$(function(){
    console.log("Page loaded");

    updatePrice();

    $("#variation-selector").change(function(){
        updatePrice();
    });

    $("#cart-button-header").collapse("hide");

    $("#go-to-cart").hide();

    $("#add-button").click(function() {
        console.log("clicked");
        var quantity = $("select#sel-quantity").val();
        var size = $("select#sel-size").val();
        var productId = $("#product-id").val();
        var data = {
            "quantity": quantity,
            "productSku": getProductId()
        };

        console.log(data);

        $.ajax({
            type: "POST",
            url: "/cart/add",
            dataType: "json",
            contentType: "application/json",
            success: function(response){
                console.log(response);
            },
            data: JSON.stringify(data)
        }).done(function(response){
            console.log('response: ');
            console.log(response);
            $("#go-to-cart").show();
        });

    });
});

function getProductId(){
    if($("#variation-selector").length ){
        return $("#sel-size").val().split("/")[0];
    } else {
        return $("#single-variation-data").val().split("/")[0];
    }
}

function getPrice(){
    if($("#variation-selector").length ) {
        return $("#sel-size").val().split("/")[1];
    } else {
        return $("#single-variation-data").val().split("/")[1];
    }


}

function updatePrice(){
    $("#price").text(getPrice());
}