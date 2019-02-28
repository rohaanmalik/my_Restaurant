How to read the events.txt or recipes.txt?

New Restaurant: restaurant name | Cuisine | number of managers | number of cooks | number of servers | number of tables
e.g. OurRestaurant | American | 1 | 2 | 4 | 5

Online or Offline: online/offline | list of employee ids that are online/offline
e.g. online | (1, 2, 3, 4, 5, 6)

Ordering: order | table number | server num | order (order item(+Changes/-Changes))
E.g. order | 3 | 4 | burger(+cheese), pizza(+cheese)

Food prepared by the chef: prepared | table number | server number | order(changes)
E.g. prepared | 3 | 4 | burger(+cheese), pizza(+cheese)

Delivering the food item to the table: deliver | table number | server number | accepted/rejected{order(changes)}
An example of deliver to the table would be: deliver | 3 | 4 | accepted{burger(+cheese)}, accepted{pizza(+cheese)}

Get the bill for a table: bill | table number
e.g. bill | 3

Receive a shipment of food: receive | employee number | food item(food quantity)
e.g. receive | 4 | cheese(20)


Note:
1. When ordering a food item, the program will allow you to add any ingredient available as an extra to an order.
2. Only one server is serving the table and any other server can make any changes to the table.
3. After threshold of the inventory is below, a request is created. however,
   the restaurant will keep on taking on the orders until that the inventory is zero to create a specific order and now order for the menuItem can be created
4. No empty lines should be there for events.txt or recipes.txt
5. New menu items for the restaurant can bve created using recipes.txt
6. Every single time a restaurant is created, recipes.txt is read and the 30 inventory items are created.