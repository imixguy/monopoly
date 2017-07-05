<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  Date: 22.11.13
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

-<div id="userForm" style="">
    <form:form method="post" action="/usermanager/createnewuser.html" modelAttribute="newUserForm">
        <%--<div style="color: red; border:red solid;">--%>

        <%--</div>--%>
        <table width="100%" cellspacing="0" cellpadding="0" border="0" class="rootFrame">
            <tr>
                <td colspan="5">&nbsp;</td></tr><tr><td>&nbsp;</td>
            <td align="left" colspan="2"><spring:message code="loginform.registersyst"/>&nbsp;
                <select id="listSystem">
                    <option value="0">&nbsp;</option>
                    <option value="2">АСУ РБ</option>
                    <option value="1">АСУ Ш</option>
                    <option value="3">РТУ-ТО</option>
                    <option value="4">АСУ П</option>
                    <option value="5">АСУ Э</option>
                    <option value="6">АСУ Т</option>
                    <option value="7">АСУ В</option><option value="8">АСУ Д</option><option value="11">АСУ Л</option><option value="12">АСУ М</option><option value="9">АСУ НИТ</option>
                    <option value="10">АСУ НБТ</option></select>
            </td><td colspan="2">&nbsp;</td>
        </tr>
            <tr>
                <td colspan="5">&nbsp;</td>
            </tr>
            <tr>
                <td width="5%">&nbsp;</td>
                <td width="30%"><spring:message code="loginform.authddata"/></td>
                <td width="30%"><spring:message code="loginform.persondata"/></td>
                <td width="30%"><spring:message code="loginform.programmdata"/></td>
                <td width="5%">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="5">&nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td style="vertical-align:top">
                    <div><div><spring:message code="loginform.logins"/>*</div><div><form:input path="login"/></div><br></div>
                    <div><div><spring:message code="loginform.pass"/>*</div><div><form:password path="password"/></div></div>
                    <div><div><spring:message code="loginform.repeatpass1"/>*</div><div><form:password path="confirmPassword"/></div></div>
                </td>
                <td style="vertical-align:top">
                    <div><div><spring:message code="loginform.fname"/>*</div><div><form:input path="fname"/></div></div>
                    <div><div><spring:message code="loginform.lname"/>*</div><div><form:input path="lname"/></div></div>
                    <div><div><spring:message code="loginform.pname"/>*</div><div><form:input path="pname"/></div></div>
                    <div>&nbsp;</div>
                    <div><div><spring:message code="loginform.phonework3"/>*</div><div><form:input path="workPhone"/></div></div>
                    <div><div><spring:message code="loginform.phonemob"/></div><div><form:input path="mobilePhone"/></div></div>
                    <div><div><spring:message code="loginform.email"/></div><div><form:input path="email"/></div></div>
                        <%--<div><div><spring:message code="registrationUser.icq2"/></div><div><input type="text" style="text-align:left;"></div></div>--%>
                </td>
                <td style="vertical-align:top">
                    <%--<div><div><spring:message code="loginform.prin"/>*</div><div><select  style="width:140px;"><option value="null">Управление</option><option value="30501">НОД-1 </option><option value="30601">НОД-2 </option><option value="30701">НОД-3 </option><option value="30801">НОД-4 </option><option value="30901">НОД-5 </option><option value="31001">НОД-6 </option></select></div></div>--%>
                    <%--<div><div><spring:message code="loginform.fabrik"/></div><div><select><option></option><option value="331">ВД других дорог</option><option value="326">ВРЗ Даугавпилс</option><option value="327">ВРЗ других дорог</option><option value="328">ВРЗ Минск</option><option value="330">ВРЗ Панютино</option><option value="329">ВРЗ Попасная</option><option value="325">ВСЗ Гомель</option><option value="332">ВЧД Бессарабская</option><option value="333">ВЧД Владимир</option><option value="334">ВЧД других дорог</option><option value="335">ВЧД Запорожье</option><option value="336">ВЧД Знаменка</option><option value="337">ВЧД Молдова</option><option value="338">ВЧД Пологи</option><option value="339">ВЧД Радвилишкис</option><option value="340">ВЧД Рига</option><option value="341">ВЧД Рославль</option><option value="342">ВЧД Смоленск</option><option value="343">ВЧД Унеча</option><option value="741">ИРЦ</option><option value="351">Л других дорог</option><option value="356">ЛВЧД Москва Смол</option><option value="357">ЛП Кишинев</option><option value="362">М других дорог</option><option value="383">Медработники</option><option value="384">Милиция</option><option value="406">Могил.ваг-стр.завод</option><option value="367">НОДВ</option><option value="375">НОДМ</option><option value="382">НОДН</option><option value="385">Пограничники</option><option value="391">ПЧ Великие Луки</option><option value="389">ПЧ Даугавпилс</option><option value="390">ПЧ Смоленск</option><option value="386">Таможня</option><option value="392">ТЧ Брянск</option><option value="393">ТЧ Великие Луки</option><option value="394">ТЧ Вильнюс</option><option value="395">ТЧ Вязьма</option><option value="396">ТЧ Даугавпилс</option><option value="397">ТЧ других дорог</option><option value="398">ТЧ Конотоп</option><option value="399">ТЧ Коростень</option><option value="400">ТЧ Смоленск</option><option value="401">ТЧ Чернигов</option><option value="771">ШЛ</option><option value="402">ШЧ Великие Луки</option><option value="403">ШЧ Даугавпилс</option><option value="404">ШЧ Смоленск</option><option value="405">ШЧ Унеча</option></select></div></div>--%>
                    <%--<div><div><spring:message code="loginform.workname"/>*</div><div><select><option value="0"></option><option value="160">Бригадир иссо</option><option value="41">В</option><option value="249">ВВИ</option><option value="252">ВЗ</option><option value="250">ВИ</option><option value="251">ВО</option><option value="247">ВРБ</option><option value="248">ВРС</option><option value="238">ВЧД</option><option value="242">ВЧД оператор</option><option value="240">ВЧД ПТО</option><option value="237">ВЧДЗЭ</option><option value="87">ГТС</option><option value="42">Д</option><option value="113">ДГП</option><option value="77">ДГПС</option><option value="92">ДЗ</option><option value="91">ДИ</option><option value="275">Диспетчер</option><option value="246">ДЛВ</option><option value="271">ДЛВИнж</option><option value="83">ДНЦ</option><option value="148">ДНЦ ЦУП</option><option value="9">ДНЦО</option><option value="235">ДНЦОП</option><option value="84">ДНЦС</option><option value="18">ДО</option><option value="97">ДОВИ</option><option value="161">ДОВИТ</option><option value="15">ДОИ</option><option value="257">ДР</option><option value="49">ДС</option><option value="119">ДСБ</option><option value="7">ДСГ</option><option value="22">ДСЗ</option><option value="194">ДСЗБ</option><option value="184">ДСП</option><option value="236">ДСПО</option><option value="109">ДСПП</option><option value="118">ДССП</option><option value="258">ДТИ</option><option value="214">Зам.УРБ-УРБД</option><option value="216">Зам.УРБ-УРБТ</option><option value="215">Зам.УРБ-УРБШ</option><option value="155">И.о. ДС</option><option value="232">ИВЦ</option><option value="233">ИВЦ Брест</option><option value="181">ИВЦИ</option><option value="234">ИВЦИ Брест</option><option value="243">ИВЦИС</option><option value="29">Инженер</option><option value="261">ИРЦОп</option><option value="45">Л</option><option value="76">Мастер</option><option value="159">Мастер базы</option><option value="75">Мастер крана</option><option value="147">МР</option><option value="267">НБТЗ</option><option value="268">НБТИ</option><option value="269">НБТИС</option><option value="136">НВП</option><option value="137">НВПЗ</option><option value="57">НГ</option><option value="26">НГЧ</option><option value="220">НГЧГ</option><option value="218">НГЧЗК</option><option value="221">НГЧЗЭ</option><option value="127">НГЧИ</option><option value="219">НГЧИнсп</option><option value="217">НГЧТ</option><option value="56">НЗ</option><option value="59">НЗТВ</option><option value="151">НИТЗ-1</option><option value="244">НИТЗТ</option><option value="229">НИТИСЗ</option><option value="3">НОД</option><option value="37">НОДВ</option><option value="5">НОДГ</option><option value="2">НОДГ-ЗН</option><option value="128">НОДГС</option><option value="199">НОДЗ</option><option value="54">НОДЗ-1</option><option value="4">НОДЗ-УРБ</option><option value="19">НОДЗПС</option><option value="60">НОДЗТВ</option><option value="202">НОДЛ</option><option value="62">НОДМ</option><option value="96">НОДН</option><option value="188">НОДНВИ</option><option value="72">НОДНЗ</option><option value="200">НОДНЗ1</option><option value="1">НОДНТ</option><option value="38">НОДП</option><option value="170">НОДПВИ</option><option value="35">НОДПЗ</option><option value="34">НОДПИ</option><option value="276">НОДПС</option><option value="114">НОДПШ</option><option value="156">НОДПШВИ</option><option value="112">НОДПШЗ</option><option value="157">НОДПШИ</option><option value="143">НОДПЭ</option><option value="195">НОДПЭШ</option><option value="193">НОДПЭШВИ</option><option value="192">НОДПЭШИ</option><option value="259">НОДРВИ</option><option value="260">НОДРИ</option><option value="40">НОДТ</option><option value="6">НОДТВ</option><option value="263">НОДТВИ</option><option value="106">НОДТПШ</option><option value="245">НОДУ</option><option value="39">НОДШ</option><option value="111">НОДШП</option><option value="186">НОДШПВИ</option><option value="185">НОДШПЗ</option><option value="135">НОДШПИ</option><option value="168">НОДШЭ</option><option value="187">НОДШЭВИ</option><option value="36">НОДЭ</option><option value="180">НОДЭШИ</option><option value="191">НОДЭШС</option><option value="274">НОИС</option><option value="277">НОРС</option><option value="149">НЧГД</option><option value="134">ОПМС</option><option value="173">ОПМСГ</option><option value="175">ОПМСЗ</option><option value="177">ОПМСИ</option><option value="176">ОПМСТО</option><option value="44">П</option><option value="55">ПГ</option><option value="116">ПД</option><option value="98">ПДБ</option><option value="201">ПДБМ</option><option value="16">ПДК</option><option value="86">ПДМ</option><option value="154">ПДС</option><option value="53">ПЗ</option><option value="241">ПИ</option><option value="256">ПИнж.см</option><option value="133">ПК</option><option value="132">ПК</option><option value="131">ПК-М</option><option value="145">ПМС</option><option value="171">ПМС ПТО</option><option value="88">ПМС ТО</option><option value="172">ПМСВИ</option><option value="103">ПМСГ</option><option value="198">ПМСГМ</option><option value="28">ПМСЗ</option><option value="31">ПМСИ</option><option value="163">ПМСИТ</option><option value="152">пом ДС</option><option value="212">ППС</option><option value="213">ППСГ</option><option value="146">ПР</option><option value="206">ПРБ</option><option value="46">ПРК</option><option value="174">ПРК</option><option value="66">Прораб</option><option value="32">ПЧ</option><option value="129">ПЧ ПТО</option><option value="85">ПЧБ</option><option value="78">ПЧГ</option><option value="12">ПЧД</option><option value="17">ПЧЗ</option><option value="104">ПЧИ</option><option value="183">ПЧИО</option><option value="204">ПЧИТ</option><option value="203">ПЧИЭ</option><option value="270">ПЧЛ</option><option value="272">ПЧО</option><option value="126">ПЧТ</option><option value="179">ПЧТО</option><option value="182">ПЧТТ</option><option value="117">ПЧУ</option><option value="115">РБ</option><option value="228">РБВ</option><option value="227">РБВП</option><option value="226">РБД</option><option value="63">РБЗ</option><option value="61">РБЗ-1</option><option value="225">РБО</option><option value="65">РБП</option><option value="224">РБПМ</option><option value="223">РБТ</option><option value="64">РБШ</option><option value="190">РРМ</option><option value="189">РРУ</option><option value="139">РРУ М</option><option value="158">РРУ МС</option><option value="50">РУП ПМС</option><option value="43">Т</option><option value="262">ТН</option><option value="80">ТНЦ</option><option value="82">ТНЦС</option><option value="255">ТР</option><option value="264">ТТБ</option><option value="95">ТЧ</option><option value="108">ТЧГ</option><option value="239">ТЧД</option><option value="107">ТЧЗ</option><option value="130">ТЧЗЭ</option><option value="266">ТЧИнж</option><option value="265">ТЧМИ</option><option value="254">ТЭ</option><option value="253">ТЭЗ</option><option value="100">УРБ</option><option value="69">УРБВ</option><option value="67">УРБД</option><option value="138">УРБМТ</option><option value="70">УРБП</option><option value="222">УРБС</option><option value="68">УРБТ</option><option value="71">УРБШ</option><option value="153">ЦУП</option><option value="48">Ш</option><option value="58">ШГ</option><option value="230">ШД</option><option value="231">ШДС</option><option value="73">ШЗ</option><option value="94">ШН</option><option value="207">ШН ДИСК/КТСМ</option><option value="166">ШН КИП АЛСН</option><option value="165">ШН КП АЛСН</option><option value="167">ШН ОПС</option><option value="169">ШН связи</option><option value="196">ШНД</option><option value="93">ШНС</option><option value="208">ШНС ДИСК/КТСМ</option><option value="209">ШНС связи</option><option value="124">ШНЦ</option><option value="125">ШНЦС</option><option value="8">ШР</option><option value="273">ШРСИВ</option><option value="30">ШЦМ</option><option value="164">ШЦМ ОПС</option><option value="210">ШЦМ связи</option><option value="24">ШЦМ СЦБ</option><option value="211">ШЦМС связи</option><option value="33">ШЧ</option><option value="11">ШЧГ</option><option value="13">ШЧД</option><option value="102">ШЧДС</option><option value="162">ШЧЗ</option><option value="197">ШЧИ</option><option value="178">ШЧИН</option><option value="51">ШЧМГ</option><option value="21">ШЧС</option><option value="110">ШЧТИ</option><option value="23">ШЧУ</option><option value="101">ШЧЦ</option><option value="47">Э</option><option value="74">ЭЗ</option><option value="79">ЭЗ-1</option><option value="150">ЭЦ</option><option value="27">ЭЧ</option><option value="105">ЭЧГ</option><option value="205">ЭЧЗ</option><option value="20">ЭЧЗК</option><option value="123">ЭЧЗС</option><option value="89">ЭЧК</option><option value="25">ЭЧКМ</option><option value="144">ЭЧКМонтер</option><option value="121">ЭЧКМС</option><option value="52">ЭЧР</option><option value="99">ЭЧС</option><option value="90">ЭЧСМ</option><option value="122">ЭЧСМС</option><option value="10">ЭЧТИ</option><option value="14">ЭЧЦ</option><option value="120">ЭЧЦС</option><option value="140">ЭЧЭ</option><option value="142">ЭЧЭМех</option><option value="141">ЭЧЭМонтер</option></select></div></div>--%>

                        <div><div><spring:message code="loginform.prin"/>*</div>
                            <div><form:select  style="width:140px;" path="id_dep">
                                <option value="null">Управление</option>
                                <option value="30501">НОД-1 </option>
                                <option value="30601">НОД-2 </option>
                                <option value="30701">НОД-3 </option>
                                <option value="30801">НОД-4 </option>
                                <option value="30901">НОД-5 </option>
                                <option value="31001">НОД-6 </option>
                            </form:select></div></div>

                        <div><div><spring:message code="loginform.fabrik"/></div>
                            <div><form:select path="id_ecUn">
                                <option value=""></option>
                                <option value="331">ВД других дорог</option>
                                <option value="326">ВРЗ Даугавпилс</option>
                                <option value="327">ВРЗ других дорог</option>
                                <option value="328">ВРЗ Минск</option>
                                <option value="330">ВРЗ Панютино</option>
                                <option value="329">ВРЗ Попасная</option>
                                <option value="325">ВСЗ Гомель</option>
                                <option value="332">ВЧД Бессарабская</option>
                                <option value="333">ВЧД Владимир</option>
                                <option value="334">ВЧД других дорог</option>
                                <option value="335">ВЧД Запорожье</option>
                                <option value="336">ВЧД Знаменка</option>
                                <option value="337">ВЧД Молдова</option>
                                <option value="338">ВЧД Пологи</option>
                                <option value="339">ВЧД Радвилишкис</option>
                                <option value="340">ВЧД Рига</option>
                                <option value="341">ВЧД Рославль</option>
                                <option value="342">ВЧД Смоленск</option>
                                <option value="343">ВЧД Унеча</option>
                                <option value="741">ИРЦ</option>
                                <option value="351">Л других дорог</option>
                                <option value="356">ЛВЧД Москва Смол</option>
                                <option value="357">ЛП Кишинев</option>
                                <option value="362">М других дорог</option>
                                <option value="383">Медработники</option>
                                <option value="384">Милиция</option>
                                <option value="406">Могил.ваг-стр.завод</option>
                                <option value="367">НОДВ</option>
                                <option value="375">НОДМ</option>
                                <option value="382">НОДН</option>
                                <option value="385">Пограничники</option>
                                <option value="391">ПЧ Великие Луки</option>
                                <option value="389">ПЧ Даугавпилс</option>
                                <option value="390">ПЧ Смоленск</option>
                                <option value="386">Таможня</option>
                                <option value="392">ТЧ Брянск</option>
                                <option value="393">ТЧ Великие Луки</option>
                                <option value="394">ТЧ Вильнюс</option>
                                <option value="395">ТЧ Вязьма</option>
                                <option value="396">ТЧ Даугавпилс</option>
                                <option value="397">ТЧ других дорог</option>
                                <option value="398">ТЧ Конотоп</option>
                                <option value="399">ТЧ Коростень</option>
                                <option value="400">ТЧ Смоленск</option>
                                <option value="401">ТЧ Чернигов</option>
                                <option value="771">ШЛ</option>
                                <option value="402">ШЧ Великие Луки</option>
                                <option value="403">ШЧ Даугавпилс</option>
                                <option value="404">ШЧ Смоленск</option>
                                <option value="405">ШЧ Унеча</option>
                            </form:select></div></div>

                        <div><div><spring:message code="loginform.workname"/>*</div>
                            <div><form:select path="id_proff">
                                <option value=""></option>
                                <option value="160">Бригадир иссо</option>
                                <option value="41">В</option>
                                <option value="249">ВВИ</option>
                                <option value="252">ВЗ</option>
                                <option value="250">ВИ</option>
                                <option value="251">ВО</option>
                                <option value="247">ВРБ</option>
                                <option value="248">ВРС</option>
                                <option value="238">ВЧД</option>
                                <option value="242">ВЧД оператор</option>
                                <option value="240">ВЧД ПТО</option>
                                <option value="237">ВЧДЗЭ</option>
                                <option value="87">ГТС</option>
                                <option value="42">Д</option>
                                <option value="113">ДГП</option>
                                <option value="77">ДГПС</option>
                                <option value="92">ДЗ</option>
                                <option value="91">ДИ</option>
                                <option value="275">Диспетчер</option>
                                <option value="246">ДЛВ</option>
                                <option value="271">ДЛВИнж</option>
                                <option value="83">ДНЦ</option>
                                <option value="148">ДНЦ ЦУП</option>
                                <option value="9">ДНЦО</option>
                                <option value="235">ДНЦОП</option>
                                <option value="84">ДНЦС</option>
                                <option value="18">ДО</option>
                                <option value="97">ДОВИ</option>
                                <option value="161">ДОВИТ</option>
                                <option value="15">ДОИ</option>
                                <option value="257">ДР</option>
                                <option value="49">ДС</option>
                                <option value="119">ДСБ</option>
                                <option value="7">ДСГ</option>
                                <option value="22">ДСЗ</option>
                                <option value="194">ДСЗБ</option>
                                <option value="184">ДСП</option>
                                <option value="236">ДСПО</option>
                                <option value="109">ДСПП</option>
                                <option value="118">ДССП</option>
                                <option value="258">ДТИ</option>
                                <option value="214">Зам.УРБ-УРБД</option>
                                <option value="216">Зам.УРБ-УРБТ</option>
                                <option value="215">Зам.УРБ-УРБШ</option>
                                <option value="155">И.о. ДС</option>
                                <option value="232">ИВЦ</option>
                                <option value="233">ИВЦ Брест</option>
                                <option value="181">ИВЦИ</option>
                                <option value="234">ИВЦИ Брест</option>
                                <option value="243">ИВЦИС</option>
                                <option value="29">Инженер</option>
                                <option value="261">ИРЦОп</option>
                                <option value="45">Л</option>
                                <option value="76">Мастер</option>
                                <option value="159">Мастер базы</option>
                                <option value="75">Мастер крана</option>
                                <option value="147">МР</option>
                                <option value="267">НБТЗ</option>
                                <option value="268">НБТИ</option>
                                <option value="269">НБТИС</option>
                                <option value="136">НВП</option>
                                <option value="137">НВПЗ</option>
                                <option value="57">НГ</option>
                                <option value="26">НГЧ</option>
                                <option value="220">НГЧГ</option>
                                <option value="218">НГЧЗК</option>
                                <option value="221">НГЧЗЭ</option>
                                <option value="127">НГЧИ</option>
                                <option value="219">НГЧИнсп</option>
                                <option value="217">НГЧТ</option>
                                <option value="56">НЗ</option>
                                <option value="59">НЗТВ</option>
                                <option value="151">НИТЗ-1</option>
                                <option value="244">НИТЗТ</option>
                                <option value="229">НИТИСЗ</option>
                                <option value="3">НОД</option>
                                <option value="37">НОДВ</option>
                                <option value="5">НОДГ</option>
                                <option value="2">НОДГ-ЗН</option>
                                <option value="128">НОДГС</option>
                                <option value="199">НОДЗ</option>
                                <option value="54">НОДЗ-1</option>
                                <option value="4">НОДЗ-УРБ</option>
                                <option value="19">НОДЗПС</option>
                                <option value="60">НОДЗТВ</option>
                                <option value="202">НОДЛ</option>
                                <option value="62">НОДМ</option>
                                <option value="96">НОДН</option>
                                <option value="188">НОДНВИ</option>
                                <option value="72">НОДНЗ</option>
                                <option value="200">НОДНЗ1</option>
                                <option value="1">НОДНТ</option>
                                <option value="38">НОДП</option>
                                <option value="170">НОДПВИ</option>
                                <option value="35">НОДПЗ</option>
                                <option value="34">НОДПИ</option>
                                <option value="276">НОДПС</option>
                                <option value="114">НОДПШ</option>
                                <option value="156">НОДПШВИ</option>
                                <option value="112">НОДПШЗ</option>
                                <option value="157">НОДПШИ</option>
                                <option value="143">НОДПЭ</option>
                                <option value="195">НОДПЭШ</option>
                                <option value="193">НОДПЭШВИ</option>
                                <option value="192">НОДПЭШИ</option>
                                <option value="259">НОДРВИ</option>
                                <option value="260">НОДРИ</option>
                                <option value="40">НОДТ</option>
                                <option value="6">НОДТВ</option>
                                <option value="263">НОДТВИ</option>
                                <option value="106">НОДТПШ</option>
                                <option value="245">НОДУ</option>
                                <option value="39">НОДШ</option>
                                <option value="111">НОДШП</option>
                                <option value="186">НОДШПВИ</option>
                                <option value="185">НОДШПЗ</option>
                                <option value="135">НОДШПИ</option>
                                <option value="168">НОДШЭ</option>
                                <option value="187">НОДШЭВИ</option>
                                <option value="36">НОДЭ</option>
                                <option value="180">НОДЭШИ</option>
                                <option value="191">НОДЭШС</option>
                                <option value="274">НОИС</option>
                                <option value="277">НОРС</option>
                                <option value="149">НЧГД</option>
                                <option value="134">ОПМС</option>
                                <option value="173">ОПМСГ</option>
                                <option value="175">ОПМСЗ</option>
                                <option value="177">ОПМСИ</option>
                                <option value="176">ОПМСТО</option>
                                <option value="44">П</option>
                                <option value="55">ПГ</option>
                                <option value="116">ПД</option>
                                <option value="98">ПДБ</option>
                                <option value="201">ПДБМ</option>
                                <option value="16">ПДК</option>
                                <option value="86">ПДМ</option>
                                <option value="154">ПДС</option>
                                <option value="53">ПЗ</option>
                                <option value="241">ПИ</option>
                                <option value="256">ПИнж.см</option>
                                <option value="133">ПК</option>
                                <option value="132">ПК</option>
                                <option value="131">ПК-М</option>
                                <option value="145">ПМС</option>
                                <option value="171">ПМС ПТО</option>
                                <option value="88">ПМС ТО</option>
                                <option value="172">ПМСВИ</option>
                                <option value="103">ПМСГ</option>
                                <option value="198">ПМСГМ</option>
                                <option value="28">ПМСЗ</option>
                                <option value="31">ПМСИ</option>
                                <option value="163">ПМСИТ</option>
                                <option value="152">пом ДС</option>
                                <option value="212">ППС</option>
                                <option value="213">ППСГ</option>
                                <option value="146">ПР</option>
                                <option value="206">ПРБ</option>
                                <option value="46">ПРК</option>
                                <option value="174">ПРК</option>
                                <option value="66">Прораб</option>
                                <option value="32">ПЧ</option>
                                <option value="129">ПЧ ПТО</option>
                                <option value="85">ПЧБ</option>
                                <option value="78">ПЧГ</option>
                                <option value="12">ПЧД</option>
                                <option value="17">ПЧЗ</option>
                                <option value="104">ПЧИ</option>
                                <option value="183">ПЧИО</option>
                                <option value="204">ПЧИТ</option>
                                <option value="203">ПЧИЭ</option>
                                <option value="270">ПЧЛ</option>
                                <option value="272">ПЧО</option>
                                <option value="126">ПЧТ</option>
                                <option value="179">ПЧТО</option>
                                <option value="182">ПЧТТ</option>
                                <option value="117">ПЧУ</option>
                                <option value="115">РБ</option>
                                <option value="228">РБВ</option>
                                <option value="227">РБВП</option>
                                <option value="226">РБД</option>
                                <option value="63">РБЗ</option>
                                <option value="61">РБЗ-1</option>
                                <option value="225">РБО</option>
                                <option value="65">РБП</option>
                                <option value="224">РБПМ</option>
                                <option value="223">РБТ</option>
                                <option value="64">РБШ</option>
                                <option value="190">РРМ</option>
                                <option value="189">РРУ</option>
                                <option value="139">РРУ М</option>
                                <option value="158">РРУ МС</option>
                                <option value="50">РУП ПМС</option>
                                <option value="43">Т</option>
                                <option value="262">ТН</option>
                                <option value="80">ТНЦ</option>
                                <option value="82">ТНЦС</option>
                                <option value="255">ТР</option>
                                <option value="264">ТТБ</option>
                                <option value="95">ТЧ</option>
                                <option value="108">ТЧГ</option>
                                <option value="239">ТЧД</option>
                                <option value="107">ТЧЗ</option>
                                <option value="130">ТЧЗЭ</option>
                                <option value="266">ТЧИнж</option>
                                <option value="265">ТЧМИ</option>
                                <option value="254">ТЭ</option>
                                <option value="253">ТЭЗ</option>
                                <option value="100">УРБ</option>
                                <option value="69">УРБВ</option>
                                <option value="67">УРБД</option>
                                <option value="138">УРБМТ</option>
                                <option value="70">УРБП</option>
                                <option value="222">УРБС</option>
                                <option value="68">УРБТ</option>
                                <option value="71">УРБШ</option>
                                <option value="153">ЦУП</option>
                                <option value="48">Ш</option>
                                <option value="58">ШГ</option>
                                <option value="230">ШД</option>
                                <option value="231">ШДС</option>
                                <option value="73">ШЗ</option>
                                <option value="94">ШН</option>
                                <option value="207">ШН ДИСК/КТСМ</option>
                                <option value="166">ШН КИП АЛСН</option>
                                <option value="165">ШН КП АЛСН</option>
                                <option value="167">ШН ОПС</option>
                                <option value="169">ШН связи</option>
                                <option value="196">ШНД</option>
                                <option value="93">ШНС</option>
                                <option value="208">ШНС ДИСК/КТСМ</option>
                                <option value="209">ШНС связи</option>
                                <option value="124">ШНЦ</option>
                                <option value="125">ШНЦС</option>
                                <option value="8">ШР</option>
                                <option value="273">ШРСИВ</option>
                                <option value="30">ШЦМ</option>
                                <option value="164">ШЦМ ОПС</option>
                                <option value="210">ШЦМ связи</option>
                                <option value="24">ШЦМ СЦБ</option>
                                <option value="211">ШЦМС связи</option>
                                <option value="33">ШЧ</option>
                                <option value="11">ШЧГ</option>
                                <option value="13">ШЧД</option>
                                <option value="102">ШЧДС</option>
                                <option value="162">ШЧЗ</option>
                                <option value="197">ШЧИ</option>
                                <option value="178">ШЧИН</option>
                                <option value="51">ШЧМГ</option>
                                <option value="21">ШЧС</option>
                                <option value="110">ШЧТИ</option>
                                <option value="23">ШЧУ</option>
                                <option value="101">ШЧЦ</option>
                                <option value="47">Э</option>
                                <option value="74">ЭЗ</option>
                                <option value="79">ЭЗ-1</option>
                                <option value="150">ЭЦ</option>
                                <option value="27">ЭЧ</option>
                                <option value="105">ЭЧГ</option>
                                <option value="205">ЭЧЗ</option>
                                <option value="20">ЭЧЗК</option>
                                <option value="123">ЭЧЗС</option>
                                <option value="89">ЭЧК</option>
                                <option value="25">ЭЧКМ</option>
                                <option value="144">ЭЧКМонтер</option>
                                <option value="121">ЭЧКМС</option>
                                <option value="52">ЭЧР</option>
                                <option value="99">ЭЧС</option>
                                <option value="122">ЭЧСМС</option>
                                <option value="10">ЭЧТИ</option>
                                <option value="14">ЭЧЦ</option>
                                <option value="120">ЭЧЦС</option>
                                <option value="140">ЭЧЭ</option>
                                <option value="142">ЭЧЭМех</option>
                                <option value="141">ЭЧЭМонтер</option>
                            </form:select></div></div>

                        <div><div><spring:message code="loginform.workname2"/>*</div><div><form:input path="id_proffShort" type="text" style="text-align:left;"/></div></div>
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td colspan="5">&nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td align="left" colspan="2"><spring:message code="registrationUser.rootText"/><br> <span style="color:red;"><spring:message code="loginform.infopasslog"/></span></td>
                <td><input type="submit" value="Cохранить" style="font-weight:bold; padding: 5px 20px;"></td>
                <td>&nbsp;</td>
            </tr>
        </table>
    </form:form>
</div>