# java-spring-excercise

To start the application:

`mvn test`

Detailed API document on swagger:

`${application_url}/swagger-ui.html`


Application supports the following request:

`GET: ${application_url}/all-prices`


`GET: ${application_url}/price`


    Param:
        ticker: name of the ticker
        start_date: start of date range of the target prices
        end_date (optional): end of date range of the target prices. If not provided, end_date is set to today
    Example: http://localhost:8080/price?ticker=FB&startdate=2020-04-01&enddate=2020-05-01
     
     
`POST: ${application_url}/insert-price`


    Insert each price into db. If ticker and date exists, update instead.
    Param:
        List of prices in Application/json
    Example:
        [
        	{
        	"open": 123,
        	"high": 456,
        	"low": 132,
        	"close": 165,
        	"date": "2020-03-04",
        	"volume": 125,
        	"adjClose": 652,
        	"ticker": "FB"
        	}
        ]
        
        
`DELETE: ${application_url}/delete-symbol`


    Param:
            ticker: name of the ticker
    Example: http://localhost:8080/delete-symbol?ticker=FB
    


By default application_url is http://localhost:8080/

To view the content of the db or to interact directly, go to http://localhost:8080/h2


====================================================================


Target:

Build a stock price backend server, providing functions via api requests.

basic requirements:

1. use java spring web framework and mysql 

2. post request: post/update multiple symbols and their corresponding historical price into database

3. get request: query historical price by symbols and time range

4. delete request: delete symbol and its corresponding data by symbols

supplementary requirements (bonus):

1. use threading to handle get request

2. use lock/mutex to handle post request

3. integrate with Swagger (https://swagger.io/)


For US stock price data, pls refer to Yahoo Finance API:
https://towardsdatascience.com/free-stock-data-for-python-using-yahoo-finance-api-9dafd96cad2e
