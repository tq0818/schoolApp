var jcrop_apis;
(function($) {
    var boundx, boundy, $preview, $preview2, $preview3, $pcnt, $pcnt2, $pcnt3, $pimg, $pimg2, $pimg3, $img,
        xsize, xsize2, xsize3, ysize, ysize2, ysize3, minHeight, maxHeight, minWidth, maxWidth, $scale, sourceHeight, sourceWidth;
    $.init=function(initW,initH,picFlag) {
        $img = picFlag == 0?$("#target"):picFlag == 1?$("#targetVideo"):$("#targetStyle");
        	//picFlag == 1?$("#targetVideo"):$("#targetStyle");
        //风采图
//        if (picFlag == 1){
//
//            if ($('#btnOne').hasClass("btn-primary")){//竖图，反之则是横图
//                $scale = 186.57/300;
//                maxHeight = 300;
//                maxWidth = 186.57;
//                minHeight = 12.06;
//                minWidth = 7.5;
//            }else {
//                $scale = 300/188;
//                maxHeight = 188;
//                maxWidth = 300;
//                minHeight =23.5 ;
//                minWidth = 37.5;
//            }
//        }else {//封面图比例
            $scale = 300/120;
            maxHeight = 120;
            maxWidth = 300;
            minHeight = 6;
            minWidth = 15;
//        }
        sourceWidth=initW;
        sourceHeight=initH;
        var initSize=resizePic();
        var jcrop_api;
        var scale = parseInt(sourceWidth) / parseInt(sourceHeight)
        if(scale > $scale){

        }else{

        }
        jc=$img.Jcrop({
            onChange : showCoords,
            onSelect : showCoords,
            onRelease: clearCoords,
            aspectRatio : $scale,
            allowMove : true,
            bgColor : "#f2f2f2",
            borderOpacity : 0.4,
            maxSize : [ initSize.w, initSize.h ],
            minSize : [ minWidth, minHeight ],
            bgFade : true,
            allowSelect : false,
            allowResize : true,
            sideHandles : false
        }, function() {
            jc=jcrop_api = this;
            var bounds = this.getBounds();
            console.log(bounds);
            boundx = bounds[0];
            boundy = bounds[1];
            var scale = parseInt(sourceWidth) / parseInt(sourceHeight);// 长宽比例
            var size = resizePic();
            if (scale > $scale) {
                jcrop_api.animateTo([0,0,boundx*$scale,boundy*$scale],function () {

                })
            } else {
                jcrop_api.animateTo([0,0,boundx*scale,boundy*scale],function () {

                })

            }
        });
        jcrop_apis = jcrop_api;

    }

    function updatePreview(c) {
        if (parseInt(c.w) > 0) {
            var rx = xsize / c.w;
            var rx2 = xsize2 / c.w;
            var rx3 = xsize3 / c.w;
            var ry = ysize / c.h;
            var ry2 = ysize2 / c.h;
            var ry3 = ysize3 / c.h;

            $pimg.css({
                width : Math.round(rx * boundx) + 'px',
                height : Math.round(ry * boundy) + 'px',
                marginLeft : '-' + Math.round(rx * c.x) + 'px',
                marginTop : '-' + Math.round(ry * c.y) + 'px'
            });

            $pimg2.css({
                width : Math.round(rx2 * boundx) + 'px',
                height : Math.round(ry2 * boundy) + 'px',
                marginLeft : '-' + Math.round(rx2 * c.x) + 'px',
                marginTop : '-' + Math.round(ry2 * c.y) + 'px'
            });

            $pimg3.css({
                width : Math.round(rx3 * boundx) + 'px',
                height : Math.round(ry3 * boundy) + 'px',
                marginLeft : '-' + Math.round(rx3 * c.x) + 'px',
                marginTop : '-' + Math.round(ry3 * c.y) + 'px'
            });
        }
    }

    function showCoords(c) {
        $('#x').val(c.x);
        $('#y').val(c.y);
        $('#w').val(c.w);
        $('#h').val(c.h);
        // $('#x2').val(c.x2);
        // $('#y2').val(c.y2);


        // updatePreview(c);
    }

    function clearCoords() {
        $('#x').val('');
        $('#y').val('');
        $('#w').val('');
        $('#h').val('');
    }

    function resizePic() {
        var h, w, ml, mt;
        var scale = parseInt(sourceWidth) / parseInt(sourceHeight);// 长宽比例
        if (scale > $scale) {
            // 过宽,宽为100%，高按比例缩
            h = maxWidth * sourceHeight/ sourceWidth;
            w = maxWidth;
            ml = 0;
            mt = (maxHeight - h) / 2;
            $img.css("height", h+"px").css("width", w+"px");
            // 改左侧图大小
            $('.jcrop-holder').find("img").css("height", h + "px").css("width", w + "px");
            // $('.jcrop-holder').css("height", h + "px").css("width",w + "px");
            // $('.jcrop-holder').css("height", h + "px").css("width",w + "px");
        } else {
            // 过高,高为100%，宽按比例缩
            h = maxHeight;
            w = maxHeight * sourceWidth/sourceHeight;
            ml = (maxWidth - w) / 2;
            mt = 0;
            $img.css("height", h+"px").css("width", w+"px");
            // 改左侧图大小
            $('.jcrop-holder').find("img").css("height", h + "px").css("width", w + "px");
            // $('.jcrop-holder').css("height", h + "px").css("width",w + "px");
            // $('.jcrop-holder').css("height", h + "px").css("width",w + "px");
        }
        var c = {};
        c.w = w;
        c.h = h;
        c.ml = ml;
        c.mt = mt;
        return c;
    }

    $(document).ready(function() {
//		$.init(500,280);
    })
    //
    // // 图片截图
    // function($){
    // var jcrop_api,
    // boundx,
    // boundy,
    // $preview = $('#preview-pane'),
    // $pcnt = $('#preview-pane .preview-container'),
    //
    // $pimg = $('#preview-pane .preview-container img'),
    // xsize = $pcnt.width(),
    // ysize = $pcnt.height();
    //
    // $('#target').Jcrop({
    // onChange: showCoords,
    // onSelect: showCoords,
    // onRelease: clearCoords,
    // onChange: updatePreview,
    // onSelect: updatePreview,
    // aspectRatio: xsize / ysize,
    // bgFade: true,
    // bgOpacity: .2,
    // setSelect: [ 60, 70, 540, 330 ]
    // },function(){
    //
    // var bounds = this.getBounds();
    // boundx = bounds[0];
    // boundy = bounds[1];
    //
    // jcrop_api = this;
    // $preview.appendTo(jcrop_api.ui.holder);
    // $('#coords').on('change','input',function(e){
    // var x1 = $('#x1').val(),
    // x2 = $('#x2').val(),
    // y1 = $('#y1').val(),
    // y2 = $('#y2').val();
    // jcrop_api.setSelect([x1,y1,x2,y2]);
    // });
    //
    // });
    //
    //
    // function updatePreview(c)
    // {
    // if (parseInt(c.w) > 0)
    // {
    // var rx = xsize / c.w;
    // var ry = ysize / c.h;
    //
    // $pimg.css({
    // width: Math.round(rx * boundx) + 'px',
    // height: Math.round(ry * boundy) + 'px',
    // marginLeft: '-' + Math.round(rx * c.x) + 'px',
    // marginTop: '-' + Math.round(ry * c.y) + 'px'
    // });
    // }
    // };
    //
    // }
    //
    // function showCoords(c)
    // {
    // $('#x1').val(c.x);
    // $('#y1').val(c.y);
    // $('#x2').val(c.x2);
    // $('#y2').val(c.y2);
    // $('#w').val(c.w);
    // $('#h').val(c.h);
    // };
    //

})(jQuery)