# Important CF commands 
1. cf apps - To list all the apps.  
2. cf e app-name -- To list all the environment variables in an app
3. cf scale app-name -i 2 scale up the instance to total of 2
4. cf set-env app-name PHONE_SERVICE_URI http://pcf.antony.cf.io to set the environment variable
5. cf restage app-name -- Restage the server
