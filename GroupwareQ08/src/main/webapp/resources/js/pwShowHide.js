$(document).ready(function(){
    $('#icon, #icon1, #icon2, #pwCheckIcon, #pwCheckIcon2').on('click',function(){
        $('input').toggleClass('active');
        if($('input').hasClass('active')){
            $(this).attr('class',"fa fa-eye-slash")
            .prev('input').attr('type',"text");
        }else{
            $(this).attr('class',"fa fa-eye")
            .prev('input').attr('type','password');
        }
    });
});
