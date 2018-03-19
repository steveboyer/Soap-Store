$(function(){
    console.log("Page loaded");
    $("#cart-button-header").collapse("hide");

    // $("#add_button").click(function() {
    //     console.log("clicked");
    //     var quantity = $("select#sel-quantity").val();
    //     var scent = $("select#sel-scent").val();
    //     var size = $("select#sel-size").val();
    //     console.log(quantity + " " + scent + " " + size);
    //     var productId = "KKJJEKD";
    //     var data = {
    //         "productId": productId,
    //         "quantity": quantity,
    //         "scent": scent,
    //         "size": size
    //     };
    //
    //     $.ajax({
    //         type: "POST",
    //         url: "/store/item/add",
    //         dataType: "json",
    //         contentType: "application/json",
    //         success: function(response){
    //             console.log(response);
    //         },
    //         data: JSON.stringify(data)
    //     }).done(function(response){
    //         console.log(response);
    //         $("#cart-button-header").collapse("show");
    //     });
    //
    // });
});

