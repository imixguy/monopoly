(function( $ ) {
    $('#startR').click(function(){
        razbor($('#textF').val());
    });
    if(window.File && window.FileReader && window.FileList && window.Blob) {
        onload = function () {
            var inp = document.querySelector('input'),
                progressBar = document.querySelector('progress');

            inp.addEventListener('change', function (e) {
                var file = e.target.files[0],
                    fr = new FileReader();

                fr.onprogress = function (e) {
                    var loaded = Math.round((e.loaded / e.total) * 100);
                    progressBar.value = loaded;
                }

                fr.onload = function () {
                    razbor(fr.result);
                }

                fr.onerror = function (e) {
                    switch(e.target.error.code) {
                        case e.target.error.NOT_FOUND_ERR:
                            alert('Файл не найден!');
                            break;
                        case e.target.error.NOT_READABLE_ERR:
                            alert('Невозможно прочитать файл!');
                            break;
                        case e.target.error.ABORT_ERR:
                            break;
                        default:
                            alert('Ошибка при чтении файла!');
                    }
                }

                fr.readAsBinaryString(file);
            }, false);
        }
    } else {
        alert("Ваш браузер не поддерживает file API");
    }
    document.keydown(function(e){(e.keyCode=17)});
    function razbor(res){
        $('#cont').empty();
        var myRe3=/\d{1,2}$/g
        var myRe4=/\d\d:\d\d.*/g
        var myRe1=/([A-Za-z])\w+/ig
        var myRe2=/.+$/gm
        var myArray = res.match(myRe2);
        $(myArray).each(function(ind,el){
            if(myRe3.test(el) | myRe4.test(el)){
                $('#cont').append($('<div></div>').append(el));
            }else{
                var myArray2 =el.match(myRe1);
                var d=$('<div></div>');
                $(myArray2).each(function(ind,el2){
                    d.append($('<span></span>').append(el2+" "))
                });

                $('#cont').append(d);

            }
        });
        $('#cont span').click(function(el){
            var t=$('<div>').append($('<div style=""></div>').append(el.target.innerHTML));
            var url2 = 'https:/translate.google.com/translate_a/single?client=t&sl=en&tl=ru&hl=ru&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=t&dt=at&ie=UTF-8&oe=UTF-8&otf=1&ssel=3&tsel=6&kc=3&tk=521264|197017&q='+el.target.innerHTML;

            url2 = 'https://translate.google.com/#en/ru/'+el.target.innerHTML;
            //t.append($('<div>').append('<iframe src="'+url2+'"/>'));
            $('#word').append(t);

            t.click(function(el){$(el.target).remove();})
        });
    }
})(jQuery)