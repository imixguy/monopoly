<%--
  Created by IntelliJ IDEA.
  User: miha
  Date: 25.09.2014
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="/resources/script/ckeditor/ckeditor.js"></script>

<jsp:include page="../../htmll2.jsp"/>

<textarea name="editor1" id="editor1" rows="10" cols="80">
    This is my textarea to be replaced with CKEditor.
</textarea>
<script>
    // Replace the <textarea id="editor1"> with a CKEditor
    // instance, using default configuration.
    CKEDITOR.replace( 'editor1' );
</script>

<table cellspacing="1" cellpadding="10" align="left" width="100%">
<tbody>
<tr valign="top"><td  colspan="2">
    <a href="#0">Основные теги</a><br>
    <a href="#1">Теги  оглавления</a><br>
    <a href="#2">Атрибуты тела документа</a><br>
    <a href="#3">Теги форматирования текста </a><br>
    <a href="#4">Гиперссылки</a><br>
    <a href="#5">Форматирование </a><br>
    <a href="#6">Графические элементы </a><br>
    <a href="#7">Таблицы</a><br>
    <a href="#8">Атрибуты таблицы </a><br>
    <a href="#9">Фреймы</a><br>
    <a href="#10">Атрибуты фреймов </a><br>
    <a href="#11">Ифрейм и его атрибуты</a><br>
    <a href="#12">Формы </a><br></td>
</tr>
<tr valign="top">
    <td valign="top"  colspan="2"><font color="#ff0000"><a name="0">Основные теги</a></font></td>
</tr>
<tr>
    <td valign="top" ><font >&lt;html&gt;&lt;/html&gt; </font> </td>
    <td >Указывает программе просмотра страниц что это HTML документ.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;head&gt;&lt;/head&gt;</font> </td>
    <td >Определяет место, где помещается различная информация не отображаемая в теле документа. Здесь располагается тег названия документа и теги для поисковых машин.</td>
</tr>
<tr><td valign="top" ><font >&lt;body&gt;&lt;/body&gt; </font></td>
    <td >Определяет видимую часть документа</td>
</tr>
<tr>
    <td valign="top"  colspan="2"><font color="#ff0000"><a target="" name="1">Теги  оглавления</a></font></td>
</tr>
<tr>
    <td valign="top" ><font >&lt;base href="www.?"&gt; </font></td>
    <td >Указание браузеру от какого базового адреса все ссылки (кроме ссылок явно прописанных полностью)</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;base target="?"&gt; </font></td>
    <td >Указание браузеру от какого базового окна в котором открываются все ссылки (кроме ссылок с отдельным указанием данного параметра)</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;meta name="allow-search" content="?"&gt; </font></td>
    <td >Указание для поисковых роботов как следует сканировать данную страницу</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;meta http-equiv="distribution" content="?"&gt; </font></td>
    <td >Указание для поисковых роботов относится ли данная страница к мировым</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;meta name="robots" content="?"&gt; </font></td>
    <td >Указание для поисковых роботов как следует сканировать данную страницу</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;meta name="author" content="?"&gt; </font></td>
    <td >Указание автора странички</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;meta name="keywords" content="?"&gt; </font></td>
    <td > Описание содержащийся информации (для поисковых машин)</td>
</tr>
<tr><td valign="top" ><font >&lt;meta http-eguiv="content-type" content="text/plain;churset="?"&gt;</font></td>
    <td >Указание браузеру в какой кодировке следует подгружать страницу (Window-1251, KOI8-R, KOI8-U, ISO-8859-5, UTF-8, UTF-16 и д.р.)<br>
        <font size="1">Некоторые хостинги (восновном иностранные) автоматически перекодируют страници получаемые сервером в определенную кодировку. Поэтому уточняйте передпостановкой этого тега у поставщиков хостинга этот вопрос.</font></td>
</tr>
<tr>
    <td valign="top" ><font >&lt;meta name="description" content="?"&gt; </font></td>
    <td >Ключевые слова странички (для поисковых машин)</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;title&gt;&lt;/title&gt; </font></td>
    <td >Помещает название  документа в оглавление программы просмотра страниц</td>
</tr>
<tr>
    <td valign="top"  colspan="2"><font color="#ff0000"><a name="2">Атрибуты тела документа</a></font></td>
</tr>
<tr>
    <td valign="top" ><font >&lt;body bgcolor="?"&gt; </font></td>
    <td >Устанавливает цвет фона документа, используя значение цвета в виде RRGGBB - пример: FF0000
        - красный цвет.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;body text="?"&gt; </font></td>
    <td >Устанавливает цвет текста документа, используя значение цвета в виде RRGGBB - пример: 000000
        - черный цвет.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;body link="?"&gt; </font></td>
    <td >Устанавливает цвет гиперссылок, используя значение цвета в виде RRGGBB - пример: 00FF00 - зеленый
        цвет.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;body vlink="?"&gt; </font></td>
    <td >Устанавливает цвет гиперссылок, на которых вы уже побывали, используя значение цвета в виде RRGGBB  - пример: 333333 - серый цвет.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;body alink="?"&gt; </font></td>
    <td >Устанавливает цвет гиперссылок при нажатии.</td>
</tr>
<tr>
    <td valign="top"  colspan="2">
        <font color="#ff0000"><a name="3">Теги форматирования текста</a></font></td>
</tr>
<tr>
    <td valign="top" ><font >&lt;pre&gt;&lt;/pre&gt;</font></td>
    <td >Обрамляет предварительно отформатированный текст.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;h1&gt;&lt;/h1&gt;</font></td>
    <td >Создает самый большой заголовок</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;h2&gt;&lt;/h2&gt;, &lt;h3&gt;, &lt;/h3&gt;&lt;h4&gt;&lt;/h4&gt;, &lt;h5&gt;, &lt;/h5&gt;</font></td>
    <td >Создает заголовоки промежуточных размеров</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;h6&gt;&lt;/h6&gt;</font></td>
    <td >Создает самый маленький заголовок</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;b&gt;&lt;/b&gt;</font></td>
    <td >Создает жирый текст (нерекомендованный)</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;i&gt;&lt;/i&gt;</font></td>
    <td >Создает наклонный текст (нерекомендованный)</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;tt&gt;&lt;/tt&gt;</font></td>
    <td >Создает текст - имитирующий стиль печатной машинки. (нерекомендованный)</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;kbd&gt;&lt;/kbd&gt;</font></td>
    <td >Создает текст - имитирующий стиль печатной машинки. (рекомендованный)</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;var&gt;&lt;/var&gt;</font></td>
    <td >Название переменных отображается курсивом</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;cite&gt;&lt;/cite&gt;</font></td>
    <td >Выделение цитат курсивом</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;address&gt;&lt;/address&gt;</font></td>
    <td >Отображается курсивом в виде отдельного абзаца</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;em&gt;&lt;/em&gt;</font></td>
    <td >Наклонный текст (воспринимается посковыми роботами как выделение)</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;strong&gt;&lt;/strong&gt;</font></td>
    <td >Жирный текст (воспринимается посковыми роботами, как особо сильное выделение)</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;font size="?"&gt;&lt;/font&gt; </font></td>
    <td >Устанавливает размер текста в пределах от 1 до 7.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;font color="?"&gt;&lt;/font&gt; </font></td>
    <td >Устанавливает цвет текста, используя значение цвета в виде RRGGBB.</td>
</tr>
<tr>
    <td valign="top"  colspan="2"><font color="#ff0000"><a name="4">Гиперссылки</a></font></td>
</tr>
<tr>
    <td valign="top" ><font >&lt;a href="URL"&gt;&lt;/a&gt;</font></td>
    <td >Создает гиперссылку на другие сайты.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;atarget="?"&gt;&lt;/a&gt;</font></td>
    <td >Указывает в каком окне открывать гиперссылку.<br>
        <table border="0">
            <tbody><tr>
                <th><em>параметры</em></th><th><em>Значение</em></th>
            </tr><tr><td valign="top"><font >_Blank</font></td>
                <td>Загрузка содержимого страницы, заданной ссылкой, в новое пустое окно</td></tr>
            <tr><td valign="top"><font >_Parent</font></td>
                <td>Загрузка содержимого страницы, заданной ссылкой, в окно, которое содержит ссылку</td></tr>
            <tr><td valign="top"><font >_Self</font></td>
                <td>Загрузка содержимого страницы, заданной ссылкой, в окно, игнорируя используемые фреймы</td></tr>
            <tr><td valign="top"><font >_Top</font></td>
                <td>Загрузка содержимого страницы, заданной ссылкой, в окно, игнорируя использованные фреймы</td></tr>
            <tr><td valign="top">&nbsp;</td>
                <td><br>При использовании фреймов и вложенных фреймов значением может выступать имя фрейма или вложенной сетки  фреймов, приэтом страница откроется в указаной части окна.</td></tr>
            </tbody></table></td>
</tr>
<tr>
    <td valign="top" ><font >&lt;a href="NAME"&gt;&lt;/a&gt;</font></td>
    <td >Создает гиперссылку на другую страницу.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;a href="mailto:EMAIL"&gt;&lt;/a&gt;</font></td>
    <td >Создает гиперссылку вызова почтовой программы для написания письма по указанному адресу.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;a href="#name"&gt;&lt;/a&gt;</font></td>
    <td >Создает гиперссылку на метку текущей страници.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;a name="name"&gt;&lt;/a&gt;</font></td>
    <td >Отмечает часть текста, как метку для гипперссылок на странице.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;a href="NAME#name"&gt;&lt;/a&gt;</font></td>
    <td >Создает гиперссылку на метку другой страници.</td>
</tr>
<tr>
    <td valign="top"  colspan="2"><font color="#ff0000"><a name="5">Форматирование</a></font>
    </td>
</tr>
<tr>
    <td valign="top" ><font >&lt;p&gt;&lt;/p&gt;</font></td>
    <td >Создает новый параграф</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;p align="?"&gt;&lt;/p&gt;</font></td>
    <td >Выравнивает параграф относительно одной из сторон документа, значения: left, right, justify или center</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;nobr&gt;</font></td>
    <td >Запрещает перевод строки.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;wbr&gt;</font></td>
    <td >Указывает где разбивать строку для переноса при необходимости.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;br&gt;</font></td>
    <td >Вставляет перевод строки.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;blockquote&gt;&lt;/blockquote&gt;</font> </td>
    <td >Создает отступы с обеих сторон текста.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;dl&gt;&lt;/dl&gt;</font></td>
    <td >Создает список определений.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;dt&gt;</font></td>
    <td >Определяет каждый из терминов списка</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;dd&gt;</font></td>
    <td >Описывает каждое определение</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;ol&gt;&lt;/ol&gt;</font></td>
    <td >Создает нумерованный список</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;li&gt;</font></td>
    <td >Определяет каждый элемент списка и присваивает номер</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;ul&gt;&lt;/ul&gt;</font></td>
    <td >Создает ненумерованный список</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;li&gt; </font></td>
    <td >Предваряет каждый элемент списка и добавляет кружок или квадратик.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;div align="?"&gt;&lt;/div&gt;</font></td>
    <td >Важный тег используемый для форматирования больших блоков текста HTML документа, также используется  в таблицах стилей</td>
</tr>
<tr>
    <td valign="top"  colspan="2"><font color="#ff0000"><a name="6">Графические элементы</a></font>
    </td>
</tr>
<tr>
    <td valign="top" ><font >&lt;img src="name"&gt;</font></td>
    <td >Добавляет изображение в HTML документ</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;img src="name" align="?"&gt;</font></td>
    <td >Выравнивает изображение к одной из сторон документа, принимает значения: left, right, center; bottom, top, middle</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;img src="name" border="?"&gt;</font></td>
    <td >Устанавливает толщину рамки вокруг изображения</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;img src="name" vspase="?"&gt;</font></td>
    <td >Устанавливает поля сверху и снизу</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;img src="name" hspase="?"&gt;</font></td>
    <td >Устанавливает поля сбоков</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;img src="name" alt="?"&gt;</font></td>
    <td >Всплывающая подсказка при наведении на имедж</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;hr&gt;</font></td>
    <td >Добавляет в HTML документ горизонтальную линию.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;hr size="?"&gt;</font></td>
    <td >Устанавливает высоту (толщину) линии</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;hr width="?"&gt;</font></td>
    <td >Устанавливает ширину линии, можно указать ширину в пикселах или процентах.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;hr noshade&gt;</font></td>
    <td >Создает линию без тени.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;hr color="?"&gt;</font></td>
    <td >Задает линии определенный цвет. Значение RRGGBB.</td>
</tr>
<tr>
    <td valign="top"  colspan="2"><font color="#ff0000"><a name="7">Таблицы</a> </font>
    </td>
</tr>
<tr>
    <td valign="top" ><font >&lt;table&gt;&lt;/table&gt;</font></td>
    <td >Создает таблицу.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;tr&gt;&lt;/tr&gt;</font></td>
    <td >Определяет строку в таблице.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;td&gt;&lt;/td&gt;</font></td>
    <td >Определяет отдельную ячейку в таблице.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;th&gt;&lt;/th&gt;</font></td>
    <td >Определяет заголовок таблицы (нормальная ячейка с отцентрованным жирным текстом)</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;caption&gt;&lt;/caption&gt;</font></td>
    <td >Определяет подпись таблицы</td>
</tr>
<tr>
    <td valign="top"  colspan="2"><font color="#ff0000"><a name="8">Атрибуты таблицы</a></font>
        <p>
        </p></td>
</tr>
<tr>
    <td valign="top" ><font >&lt;table border="#"&gt;</font></td>
    <td >Задает толщину рамки таблицы.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;table cellspacing="#"&gt;</font></td>
    <td >Задает расстояние между ячейками таблицы.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;table cellpadding="#"&gt;</font></td>
    <td >Задает расстояние между содержимым ячейки и ее рамкой.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;table width="#"&gt;</font></td>
    <td >Устанавливает ширину таблицы в пикселах или процентах от ширины документа.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;table height="#"&gt;</font></td>
    <td >Устанавливает высоту таблицы в пикселах или процентах от высоты документа.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;tr align="?"&gt; или &lt;td align="?"&gt;</font></td>
    <td >Устанавливает выравнивание ячеек в таблице, принимает значения: left, center, или right.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;tr valign="?"&gt; или &lt;td valign="?"&gt;</font></td>
    <td >Устанавливает вертикальное выравнивание для ячеек таблицы, принимает значения : top, middle, или bottom.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;td colspan="#"&gt;</font></td>
    <td >Указывает кол-во столбцев, которое объединено в одной ячейке. (по умолчанию=1)</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;td rowspan="#"&gt;</font></td>
    <td >Указывает кол-во строк, которое объединено в одной ячейке. (по умолчанию=1)</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;td nowrap&gt;</font></td>
    <td >Не позволяет программе просмотра делать перевод строки в ячейке таблицы.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;td width="#"&gt;</font></td>
    <td >Устанавливает ширину ячейки в пикселах или процентах от ширины таблицы (ячейки одного столбца не могут иметь разную ширину).</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;td height="#"&gt;</font></td>
    <td >Устанавливает высоту ячейки в пикселах или процентах от высоты таблици (ячейки одной строки не могут иметь разную высоту).</td>
</tr>
<tr>
    <td valign="top"  colspan="2"><font color="#ff0000"><a name="9">Фреймы</a></font>
    </td>
</tr>
<tr>
    <td valign="top" ><font >&lt;frameset&gt;&lt;/frameset&gt;</font></td>
    <td >Предваряет тег &lt;body&gt; в документе, содержащем фреймы;</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;frameset rows="value,value"&gt;</font></td>
    <td >Определяет строки в таблице фреймов, высота которых определена кол-вом пикселов или в процентном  соотношении к высоте таблицы фреймов.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;frameset cols="value,value"&gt;</font></td>
    <td >Определяет столбцы  в таблице фреймов, ширина которых определена кол-вом пикселов или в процентном  соотношении к ширине таблицы фреймов.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;frame&gt;</font></td>
    <td >Определяет единичный фрейм или область в таблице фреймов.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;noframes&gt;&lt;/noframes&gt;</font></td>
    <td >Определяет, что будет показано в окне браузера, если он не поддерживает фреймы.</td>
</tr>
<tr>
    <td valign="top"  colspan="2"><font color="#ff0000"><a name="10">Атрибуты фреймов</a></font></td>
</tr>
<tr>
    <td valign="top" ><font >&lt;frame src="URL"&gt;</font></td>
    <td >Определяет какой из HTML документов будет показан во фрейме.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;frame name="name"&gt;</font></td>
    <td >Указывает Имя фрейма или области, что позволяет перенаправлять информацию в этот фрейм или область из других фреймов.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;frame marginwidth="#"&gt;</font></td>
    <td >Определяет величину отступов по левому и правому краям в нутрь фрейма; должно быть равно или больше 1.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;frame marginheight="#"&gt;</font></td>
    <td >Определяет величину отступов по верхнему и нижнему краям в нутрь фрейма; должно быть равно или больше 1.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;frame scrolling=VALUE&gt;</font></td>
    <td >Указывает будет-ли выводится линейка прокрутки во фрейме; значение value может быть "yes," "no," или "auto". Значение по умолчанию для обычных документов - auto.
    </td>
</tr>
<tr>
    <td valign="top" ><font >&lt;frame noresize&gt;</font></td>
    <td >Препятствует изменению размеров фрейма пользователем.</td>
</tr>
<tr>
    <td valign="top"  colspan="2"><font color="#ff0000"><a name="11">Ифрейм и его атрибуты</a></font></td>
</tr>
<tr>
    <td valign="top" ><font >&lt;iframe&gt;&lt;/iframe&gt;</font></td>
    <td >Создает контейнер, который может содержать любые элементы. Остальные элементы обтекают этот контейнер.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;iframe src="URL"&gt;</font></td>
    <td >Определяет какой из HTML документов будет показан в ифрейме.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;iframe name="name"&gt;</font></td>
    <td >Указывает Имя ифрейма, что позволяет перенаправлять информацию в этот ифрейм .</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;iframe vspase="?"&gt;</font></td>
    <td >Устанавливает поля сверху и снизу с наружи от ифрейма</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;iframe hspase="?"&gt;</font></td>
    <td >Устанавливает поля сбоков с наружи от ифрейма</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;iframe marginwidth="#"&gt;</font></td>
    <td >Определяет величину отступов по левому и правому краям в нутрь ифрейма; должно быть равно или больше 1.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;iframe marginheight="#"&gt;</font></td>
    <td >Определяет величину отступов по верхнему и нижнему краям в нутрь ифрейма; должно быть равно или больше 1.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;iframe scrolling=VALUE&gt;</font></td>
    <td >Указывает будет-ли выводится линейка прокрутки в ифрейме; значение value может быть "yes," "no," или "auto". Значение по умолчанию для обычных документов - auto.
    </td>
</tr><tr>
    <td valign="top" ><font >&lt;iframe width="#"&gt;</font></td>
    <td >Определяет ширину ифрейма</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;iframe height="#"&gt;</font></td>
    <td >Определяет высоту ифрейма;</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;iframe title="?"&gt;</font></td>
    <td >Текст всплывающей подсказки
    </td>
</tr>
<tr>
    <td valign="top"  colspan="2"><font color="#ff0000"><a name="12">Формы </a></font></td>
</tr>
<tr>
    <td valign="top" ><font >&lt;form&gt;&lt;/form&gt;</font></td>
    <td >Создает формы</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;select multiple name="NAME" size="?"&gt;&lt;/select&gt;</font></td>
    <td >Создает скролируемое меню. Size устанавливает кол-во пунктов меню, которое будет показано на экране, остальные будут доступны при использовании прокрутки.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;option&gt;</font></td>
    <td >Указывает каждый отдельный элемент меню</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;select name="NAME"&gt;&lt;/select&gt; </font></td>
    <td >Создает ниспадающее меню
        <p>
        </p></td></tr><tr>
    <td valign="top" ><font >&lt;option&gt; </font></td>
    <td >Указывает каждый отдельный элемент меню</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;textarea name="NAME" cols=40 rows=8&gt;&lt;/textarea&gt;</font></td>
    <td >Создает окно для ввода текста. Columns указывает ширину окна; rows указывает его высоту.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;input type="checkbox" name="NAME"&gt;</font></td>
    <td >Создает checkbox.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;input type="radio" name="NAME" value="x"&gt;</font></td>
    <td >Создает radio кнопку.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;input type=text name="foo" size=20&gt;</font></td>
    <td >Создает строку для ввода текста. Параметром Size указывается длина в символах.</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;input type="submit" value="NAME"&gt;</font></td>
    <td >Создает кнопку "Отправить"</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;input type="image" border="0" name="NAME" src="name.gif"&gt;</font></td>
    <td >Создает кнопку "Отправить" - для этого используется изображение</td>
</tr>
<tr>
    <td valign="top" ><font >&lt;input type="reset"&gt;</font></td>
    <td >Создает кнопку "Очистить" </td>
</tr>
</tbody>
</table>