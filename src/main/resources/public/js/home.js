$(function(){
    // Remove no-js class
    $('body').removeClass('no-js');

    // Animate to section when nav is clicked
    $('header a').click(function(e) {

        // Treat as normal link if no-scroll class
        if ($(this).hasClass('no-scroll')) return;

        e.preventDefault();
        var heading = $(this).attr('href');
        var scrollDistance = $(heading).offset().top;

        $('html, body').animate({
            scrollTop: scrollDistance + 'px'
        }, Math.abs(window.pageYOffset - $(heading).offset().top));

        // Hide the menu once clicked if mobile
        if ($('header').hasClass('active')) {
            $('header, body').removeClass('active');
        }
    });

    // Scroll to top
    $('#to-top').click(function() {
        $('html, body').animate({
            scrollTop: 0
        }, 500);
    });

    // Scroll to first element
    $('#lead-down-span').click(function() {
            var scrollDistance = $('#lead').next().offset().top;
            $('html, body').animate({
                scrollTop: scrollDistance + 'px'
            }, 500);
    });

    // Open mobile menu
    $('#mobile-menu-open').click(function() {
        $('header, body').addClass('active');
    });

    // Close mobile menu
    $('#mobile-menu-close').click(function() {
        $('header, body').removeClass('active');
    });

});
//
// function check_if_in_view() {
//     var window_height = $window.height();
//     var window_top_position = $window.scrollTop();
//     var window_bottom_position = (window_top_position + window_height);
//
//     $.each($animation_elements, function() {
//         var $element = $(this);
//         var element_height = $element.outerHeight();
//         var element_top_position = $element.offset().top;
//         var element_bottom_position = (element_top_position + element_height);
//
//         //check to see if this current container is within viewport
//         if ((element_bottom_position >= window_top_position) &&
//             (element_top_position <= window_bottom_position)) {
//             $element.addClass('in-view');
//         } else {
//             $element.removeClass('in-view');
//         }
//     });
// }
//
// var $animation_elements = $('.animation-element');
// var $window = $(window);
// $window.on('scroll resize', check_if_in_view);
// $window.trigger('scroll');