INSERT INTO recipe (name, ingredients, instructions, gluten_free, featured) VALUES
/*if the first recipe gets removed we'll have to update the recipe automated tests*/
('Avocado Egg Breakfast Bowl', 'eggs, avocado, spinach, salt', 'Cook eggs and assemble bowl.', TRUE, TRUE), 
('Classic Pancakes', 'flour, milk, eggs, sugar', 'Mix batter and cook on griddle.', FALSE, TRUE),
('Chicken Stir Fry', 'chicken, broccoli, soy sauce, garlic', 'Stir fry chicken and vegetables.', TRUE, TRUE),
('Beef Tacos', 'beef, tortillas, lettuce, cheese', 'Cook beef and assemble tacos.', FALSE, TRUE),
('Quinoa Veggie Salad', 'quinoa, cucumber, tomato, olive oil', 'Mix cooked quinoa with vegetables.', TRUE, TRUE),
('Spaghetti Marinara', 'spaghetti, tomato sauce, garlic', 'Cook pasta and mix with sauce.', FALSE, FALSE),
('Baked Salmon', 'salmon, lemon, garlic, olive oil', 'Bake salmon with seasoning.', TRUE, FALSE),
('Vegetable Omelette', 'eggs, peppers, onions, cheese', 'Cook eggs with vegetables.', TRUE, FALSE),
('Chicken Caesar Wrap', 'chicken, tortilla, lettuce, dressing', 'Wrap chicken and lettuce.', FALSE, FALSE),
('Sweet Potato Bowl', 'sweet potato, rice, avocado', 'Roast potato and combine ingredients.', TRUE, FALSE),
('Shrimp Fried Rice', 'rice, shrimp, soy sauce, eggs', 'Stir fry shrimp and rice.', FALSE, FALSE),
('Lentil Soup', 'lentils, carrots, onion, garlic', 'Simmer lentils with vegetables.', TRUE, FALSE),
('Turkey Sandwich', 'turkey, bread, lettuce, mayo', 'Assemble sandwich.', FALSE, FALSE),
('Grilled Chicken Salad', 'chicken, lettuce, tomato, dressing', 'Grill chicken and toss salad.', TRUE, FALSE),
('Mac and Cheese', 'pasta, cheese, milk, butter', 'Cook pasta and mix cheese sauce.', FALSE, FALSE),
('Zucchini Noodles', 'zucchini, tomato sauce, garlic', 'Spiralize zucchini and heat sauce.', TRUE, FALSE),
('Veggie Pizza', 'pizza dough, tomato sauce, vegetables', 'Bake assembled pizza.', FALSE, FALSE),
('Thai Peanut Noodles', 'rice noodles, peanut sauce, vegetables', 'Cook noodles and mix sauce.', TRUE, FALSE),
('Beef Burger', 'beef patty, bun, lettuce, tomato', 'Grill patty and assemble burger.', FALSE, FALSE),
('Grilled Fish Tacos', 'fish, corn tortillas, cabbage', 'Grill fish and assemble tacos.', TRUE, FALSE),
('Chickpea Curry', 'chickpeas, coconut milk, spices', 'Simmer chickpeas with spices.', TRUE, FALSE),
('Chicken Alfredo', 'pasta, chicken, cream sauce', 'Cook pasta and combine with chicken.', FALSE, FALSE),
('Stuffed Bell Peppers', 'peppers, rice, beef, cheese', 'Stuff peppers and bake.', TRUE, FALSE),
('Breakfast Burrito', 'eggs, tortilla, beans, cheese', 'Fill tortilla and wrap.', FALSE, FALSE),
('Caprese Salad', 'tomato, mozzarella, basil', 'Layer ingredients and drizzle oil.', TRUE, FALSE),
('Chicken Noodle Soup', 'chicken, noodles, carrots', 'Simmer chicken and vegetables.', FALSE, FALSE),
('Grilled Steak', 'steak, salt, pepper', 'Grill steak to desired doneness.', TRUE, FALSE),
('Vegetable Curry', 'mixed vegetables, coconut milk, spices', 'Cook vegetables with curry sauce.', TRUE, FALSE),
('Ham and Cheese Sandwich', 'ham, cheese, bread', 'Assemble sandwich.', FALSE, FALSE),
('Shrimp Tacos', 'shrimp, tortillas, cabbage', 'Cook shrimp and assemble tacos.', TRUE, FALSE),
('Rice and Beans', 'rice, beans, spices', 'Cook rice and beans together.', TRUE, FALSE),
('Chicken Parmesan', 'chicken, breadcrumbs, tomato sauce', 'Bread chicken and bake with sauce.', FALSE, FALSE),
('Greek Salad', 'cucumber, tomato, feta, olives', 'Mix vegetables and cheese.', TRUE, FALSE),
('Vegetable Fried Rice', 'rice, vegetables, soy sauce', 'Stir fry rice with vegetables.', FALSE, FALSE),
('Baked Chicken Thighs', 'chicken thighs, garlic, spices', 'Bake seasoned chicken.', TRUE, FALSE),
('Egg Fried Rice', 'rice, eggs, soy sauce', 'Stir fry rice and eggs.', FALSE, FALSE),
('Cobb Salad', 'chicken, bacon, lettuce, egg', 'Combine ingredients in bowl.', TRUE, FALSE),
('Spinach Pasta', 'pasta, spinach, garlic', 'Cook pasta and mix spinach.', FALSE, FALSE),
('Roasted Veggie Bowl', 'vegetables, quinoa, olive oil', 'Roast vegetables and combine.', TRUE, FALSE),
('Chicken Quesadilla', 'chicken, tortilla, cheese', 'Grill filled tortilla.', FALSE, FALSE),
('Tomato Basil Soup', 'tomatoes, basil, garlic', 'Simmer tomatoes and blend.', TRUE, FALSE),
('Beef Chili', 'beef, beans, chili powder', 'Simmer beef and beans.', TRUE, FALSE),
('Egg Salad', 'eggs, mayo, mustard', 'Mix chopped eggs and dressing.', TRUE, FALSE),
('Chicken Tikka', 'chicken, yogurt, spices', 'Marinate and grill chicken.', TRUE, FALSE),
('Garlic Butter Shrimp', 'shrimp, butter, garlic', 'Cook shrimp in butter.', TRUE, FALSE),
('BBQ Pulled Pork Sandwich', 'pork, BBQ sauce, bun', 'Slow cook pork and assemble.', FALSE, FALSE),
('Vegetable Lasagna', 'lasagna noodles, vegetables, cheese', 'Layer ingredients and bake.', FALSE, FALSE),
('Avocado Toast', 'bread, avocado, salt', 'Toast bread and spread avocado.', FALSE, FALSE),
('Grilled Halloumi Salad', 'halloumi, lettuce, tomato', 'Grill cheese and mix salad.', TRUE, FALSE),
('Chicken Rice Bowl', 'chicken, rice, vegetables', 'Cook chicken and assemble bowl.', TRUE, FALSE);

INSERT INTO restaurant (name, location, cuisine_type, gluten_free, featured, celiac_certified) VALUES
/*if the first restaurant gets removed we'll have to update the restaurant automated tests*/
('Golden Fork Bistro','France','French',TRUE,TRUE,FALSE),
('Spice Route Kitchen','India','Indian',TRUE,FALSE,TRUE),
('Roma Pasta House','Italy','Italian',FALSE,TRUE,FALSE),
('Tokyo Sakura Grill','Japan','Japanese',TRUE,TRUE,TRUE),
('Dragon Wok','China','Chinese',FALSE,FALSE,FALSE),
('Casa Mexicana','Mexico','Mexican',TRUE,TRUE,FALSE),
('Bangkok Street Bites','Thailand','Thai',TRUE,FALSE,TRUE),
('Mediterranean Garden','Greece','Mediterranean',TRUE,TRUE,TRUE),
('Berlin Sausage Hall','Germany','German',FALSE,FALSE,FALSE),
('Seoul BBQ Corner','South Korea','Korean',FALSE,TRUE,FALSE),
('Cairo Spice Market','Egypt','Middle Eastern',TRUE,FALSE,FALSE),
('Istanbul Kebab House','Turkey','Turkish',TRUE,TRUE,TRUE),
('Rio Grill House','Brazil','Brazilian',FALSE,TRUE,FALSE),
('Maple Leaf Diner','Canada','Canadian',TRUE,TRUE,TRUE),
('Sydney Seafood Shack','Australia','Seafood',TRUE,FALSE,FALSE),
('Cape Town Braai','South Africa','African',FALSE,FALSE,FALSE),
('Lisbon Ocean Table','Portugal','Portuguese',TRUE,TRUE,FALSE),
('Barcelona Tapas Bar','Spain','Spanish',FALSE,TRUE,FALSE),
('Havana Nights Cafe','Cuba','Caribbean',TRUE,FALSE,FALSE),
('Peruvian Andes Kitchen','Peru','Peruvian',TRUE,TRUE,TRUE),
('Buenos Aires Steakhouse','Argentina','Steakhouse',FALSE,TRUE,FALSE),
('Viking Feast Hall','Norway','Nordic',TRUE,FALSE,FALSE),
('Stockholm Fish Market','Sweden','Seafood',TRUE,TRUE,TRUE),
('Dublin Pub Kitchen','Ireland','Irish',FALSE,FALSE,FALSE),
('Prague Castle Tavern','Czech Republic','Eastern European',FALSE,TRUE,FALSE),
('Budapest Paprika House','Hungary','Hungarian',TRUE,FALSE,FALSE),
('Warsaw Pierogi Bar','Poland','Polish',FALSE,FALSE,FALSE),
('Athens Olive Table','Greece','Greek',TRUE,TRUE,TRUE),
('Alexandria Falafel Corner','Egypt','Middle Eastern',TRUE,TRUE,TRUE),
('Beirut Cedar Grill','Lebanon','Lebanese',TRUE,FALSE,FALSE),
('Dubai Desert Kitchen','UAE','Arabic',TRUE,TRUE,TRUE),
('Tehran Saffron House','Iran','Persian',TRUE,FALSE,FALSE),
('Kathmandu Spice Cafe','Nepal','Nepalese',TRUE,FALSE,FALSE),
('Hanoi Pho Corner','Vietnam','Vietnamese',TRUE,TRUE,TRUE),
('Manila Street Eats','Philippines','Filipino',FALSE,FALSE,FALSE),
('Jakarta Satay Grill','Indonesia','Indonesian',TRUE,FALSE,FALSE),
('Kuala Lumpur Spice Hub','Malaysia','Malaysian',TRUE,TRUE,FALSE),
('Singapore Fusion Kitchen','Singapore','Fusion',TRUE,TRUE,TRUE),
('Taipei Dumpling House','Taiwan','Taiwanese',FALSE,FALSE,FALSE),
('Moscow Red Square Cafe','Russia','Russian',FALSE,FALSE,FALSE),
('Zurich Alpine Table','Switzerland','Swiss',TRUE,TRUE,FALSE),
('Vienna Schnitzel Haus','Austria','Austrian',FALSE,TRUE,FALSE),
('Brussels Waffle Corner','Belgium','Belgian',FALSE,FALSE,FALSE),
('Amsterdam Canal Bistro','Netherlands','Dutch',TRUE,FALSE,FALSE),
('Copenhagen Nordic Kitchen','Denmark','Nordic',TRUE,TRUE,TRUE),
('Reykjavik Ice Grill','Iceland','Nordic',TRUE,FALSE,FALSE),
('Helsinki Harbor Cafe','Finland','Nordic',TRUE,TRUE,TRUE),
('Tallinn Old Town Kitchen','Estonia','Baltic',TRUE,FALSE,FALSE),
('Riga Baltic Bites','Latvia','Baltic',TRUE,FALSE,FALSE),
('Vilnius Forest Table','Lithuania','Baltic',TRUE,TRUE,FALSE);

/*test reviews*/
INSERT INTO restaurant_review (rating, text, restaurant_id, date) VALUES (5, 'Excellent food and great service!', 1, '2024-06-01');
INSERT INTO restaurant_review (rating, text, restaurant_id, date) VALUES (4, 'Nice atmosphere and tasty dishes.', 1, '2024-06-02');
/*commented out so other stuff can be tested

##### INCOMPLETE SINCE USERS IS NOT DEFINED YET ######
INSERT INTO restaurant_review (user_id, rating, text, restaurant_id) VALUES (1, 5, 'Excellent food and great service!', 1);
INSERT INTO restaurant_review (user_id, rating, text, restaurant_id) VALUES (2, 4, 'Nice atmosphere and tasty dishes.', 2);
INSERT INTO restaurant_review (user_id, rating, text, restaurant_id) VALUES (3, 3, 'Food was decent but service was slow.', 3);
INSERT INTO restaurant_review (user_id, rating, text, restaurant_id) VALUES (4, 5, 'Absolutely loved the gluten-free options!', 4);
INSERT INTO restaurant_review (user_id, rating, text, restaurant_id) VALUES (5, 4, 'Great spot for dinner with friends.', 5);
INSERT INTO restaurant_review (user_id, rating, text, restaurant_id) VALUES (6, 2, 'Food was cold when it arrived.', 6);
INSERT INTO restaurant_review (user_id, rating, text, restaurant_id) VALUES (7, 5, 'Fantastic experience overall!', 7);
INSERT INTO restaurant_review (user_id, rating, text, restaurant_id) VALUES (8, 4, 'Very good food, would come again.', 8);
INSERT INTO restaurant_review (user_id, rating, text, restaurant_id) VALUES (9, 3, 'Average experience but not bad.', 9);
INSERT INTO restaurant_review (user_id, rating, text, restaurant_id) VALUES (10, 5, 'One of my favorite restaurants now!', 10);

INSERT INTO recipe_review (user_id, rating, text, recipe_id) VALUES (1, 5, 'Amazing recipe, very easy to follow!', 1);
INSERT INTO recipe_review (user_id, rating, text, recipe_id) VALUES (2, 4, 'Tasted great but I added more seasoning.', 2);
INSERT INTO recipe_review (user_id, rating, text, recipe_id) VALUES (3, 3, 'Decent but the instructions were slightly confusing.', 3);
INSERT INTO recipe_review (user_id, rating, text, recipe_id) VALUES (4, 5, 'Perfect! Will definitely make again.', 4);
INSERT INTO recipe_review (user_id, rating, text, recipe_id) VALUES (5, 4, 'Really good, family loved it.', 5);
INSERT INTO recipe_review (user_id, rating, text, recipe_id) VALUES (6, 2, 'Not my favorite, a bit bland.', 6);
INSERT INTO recipe_review (user_id, rating, text, recipe_id) VALUES (7, 5, 'Fantastic flavor and simple ingredients.', 7);
INSERT INTO recipe_review (user_id, rating, text, recipe_id) VALUES (8, 4, 'Turned out very well, good instructions.', 8);
INSERT INTO recipe_review (user_id, rating, text, recipe_id) VALUES (9, 3, 'It was okay, might tweak it next time.', 9);
INSERT INTO recipe_review (user_id, rating, text, recipe_id) VALUES (10, 5, 'Loved it! One of the best recipes I tried.', 10);

####### THESE DO NOT HAVE ENTITIES, CONTROLLERS, OR REPOSITORIES YET ########
INSERT INTO menu_items (restaurant_id, item_name, celiac_certified)
(1, 'sloppy tony', TRUE)

INSERT INTO meals (id, name, type, date)
VALUES (1, 'Ragnars mead', 3, '793-03-08')

INSERT INTO users (username, password_hash, email, year_joined)
VALUES ('john_doe', 'hello-johnDoe:)', 'john@Doe.com', 2026);
*/
