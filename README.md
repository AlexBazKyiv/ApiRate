При запуску проекту відбувається автоматичне отримання даних по url : https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange
Данні формату XML зберігаються та оброблюються за допомогою  DOM. (Document Object Model) DOM-обробник зчитує одразу увесь XML та зберігає його, створює ієрархію у  виді дерева, по якій мы можемо спокійно рухатись і отримувати доступ до потрібних нам вузлів та їх елементів. Такими елементами є внутрішні елементи currency а саме - r030, txt, rate, cc.
Приклад отримуваного XML:
<exchange>
<currency>
<r030>36</r030>
<txt>Австралійський долар</txt>
<rate>21.4691</rate>
<cc>AUD</cc>
<exchangedate>11.02.2021</exchangedate>
</currency>
<currency>
<r030>124</r030>
<txt>Канадський долар</txt>
<rate>21.872</rate>
<cc>CAD</cc>
<exchangedate>11.02.2021</exchangedate>
</currency>

Отримнні за допомогою DOM-обробника значення зберігаємо в ArrayList<CurrencyRate> rates. Для цієї записі клас CurrencyRate має поля r030, txt, rate, cc.
Фінальним пунктом автоматичної загрузки є зберігання в таблицю in-memory бази даних Н2.
 
Controller ExchangeRateController відповідає на метод Get ("/rate") та видає увесь список даних в форматі JSON:
 

 

 

