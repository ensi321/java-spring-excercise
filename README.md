# java-spring-excercise

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
