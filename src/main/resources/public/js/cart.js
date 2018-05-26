$(function(){
    console.log("Page loaded");

    updatePrice();

    $("#variation-selector").change(function(){
        updatePrice();
    });

    $("#cart-button-header").collapse("hide");

    var active = function(){
      switch ($("#view-name").val()){
          case "home":
              $("#nav-home").addClass("active");
              break;
          case "cart":
              $("#nav-cart").addClass("active");
              break;
          case "about":
              $("#nav-about").addClass("active");
              break;
          case "categories":
              $("#dropdownMenuLink").addClass("active");
              break;
          case "contact":
              $("#nav-contact").addClass("active");
              break;
      }

    };

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

    $('.btn-number').click(function(e){
        e.preventDefault();

        fieldName = $(this).attr('data-field');
        type      = $(this).attr('data-type');
        var input = $("input[name='"+fieldName+"']");
        var currentVal = parseInt(input.val());
        if (!isNaN(currentVal)) {
            if(type === 'minus') {

                if(currentVal > input.attr('min')) {
                    input.val(currentVal - 1).change();
                }
                if(parseInt(input.val()) === input.attr('min')) {
                    $(this).attr('disabled', true);
                }

            } else if(type === 'plus') {

                if(currentVal < input.attr('max')) {
                    input.val(currentVal + 1).change();
                }
                if(parseInt(input.val()) === input.attr('max')) {
                    $(this).attr('disabled', true);
                }

            }
        } else {
            input.val(0);
        }
    });




    $('.input-number').focusin(function(){
        $(this).data('oldValue', $(this).val());
    }).change(function() {

        minValue =  parseInt($(this).attr('min'));
        maxValue =  parseInt($(this).attr('max'));
        valueCurrent = parseInt($(this).val());

        //name = $(this).attr('name');
        if(valueCurrent >= minValue) {
            $(".btn-number[data-type='minus'][data-field='"+name+"']").removeAttr('disabled')
        } else {
            alert('Sorry, the minimum value was reached');
            $(this).val($(this).data('oldValue'));
        }
        if(valueCurrent <= maxValue) {
            $(".btn-number[data-type='plus'][data-field='"+name+"']").removeAttr('disabled')
        } else {
            alert('Sorry, the maximum value was reached');
            $(this).val($(this).data('oldValue'));
        }


    }).keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 190]) !== -1 ||
            // Allow: Ctrl+A
            (e.keyCode == 65 && e.ctrlKey === true) ||
            // Allow: home, end, left, right
            (e.keyCode >= 35 && e.keyCode <= 39)) {
            // let it happen, don't do anything
            return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
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

