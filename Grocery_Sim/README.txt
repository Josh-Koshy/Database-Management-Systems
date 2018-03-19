Hey guys I uploaded the final version of my java code that generated the
sql code for our submission for phase 1. I also included the csv files
that I used to populate our warehouses, dist_stations, customers, and 
items tables.

My java code for GroceryDB and GrocerySim isn't pretty but it worked.
If it was required for our submission it would have been much sexier. Feel
free to play with it as much as you like. Thats what she said...

Only major note is that when you run GrocerySim with the -create option to
generate our db's tables, the output will always be the same. However if you
run it with -insert flag, the generated sql code will be different on every
execution given that the transaction simulator that I made relies on randomly
genereated numbers.