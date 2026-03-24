INSERT INTO recipe (name, ingredients, instructions, gluten_free, featured) VALUES
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

INSERT INTO restaurant (name, location, cuisine_type, gluten_free, featured, celiac_certified)
VALUES
    ('Riz Gluten-Free Asian Kitchen', '3471 Yonge St, North York, Toronto, ON', 'Asian', TRUE, TRUE, TRUE),
    ('Almond Butterfly Bistro', '792 Dundas St W, Toronto, ON', 'Canadian', TRUE, TRUE, TRUE),
    ('Almond Butterfly Bakeshop & Cafe', '100 Harbord St, Toronto, ON', 'Bakery/Cafe', TRUE, FALSE, TRUE),
    ('El Pocho Antojitos Bar', '2 Follis Ave, Toronto, ON', 'Mexican', TRUE, TRUE, TRUE),
    ('Bunners Bakeshop', '244 Augusta Ave, Toronto, ON', 'Bakery', TRUE, FALSE, TRUE),
    ('The Dirty Bird Chicken & Waffles', '79 Kensington Ave, Toronto, ON', 'American', TRUE, FALSE, FALSE),
    ('Riz on St. Clair', '760 St. Clair Ave W, Toronto, ON', 'Asian', TRUE, FALSE, TRUE),
    ('Barque Smokehouse', '299 Roncesvalles Ave, Toronto, ON', 'BBQ', TRUE, FALSE, FALSE),
    ('Bangkok Garden', '18 Elm St, Toronto, ON', 'Thai', TRUE, FALSE, FALSE),
    ('The Basil Box', '460 Yonge St, Toronto, ON', 'Southeast Asian', TRUE, FALSE, FALSE),
    ('Senla Vietnamese', '133 Richmond St W, Toronto, ON', 'Vietnamese', TRUE, TRUE, TRUE),
    ('Impact Kitchen Corktown', '573 King St E, Toronto, ON', 'Health/Bowls', TRUE, FALSE, TRUE),
    ('Impact Kitchen King West', '444 Adelaide St W, Toronto, ON', 'Health/Bowls', TRUE, FALSE, TRUE),
    ('Kupfert & Kim', '100 King St W, Toronto, ON', 'Plant-Based', TRUE, FALSE, TRUE),
    ('On Third Thought', '6 Markham St, Toronto, ON', 'Dessert/Gelato', TRUE, FALSE, TRUE),
    ('Playa Cabana', '111 Dupont St, Toronto, ON', 'Mexican', TRUE, FALSE, FALSE),
    ('Byblos Downtown', '11 Duncan St, Toronto, ON', 'Mediterranean', TRUE, FALSE, FALSE),
    ('Bar Isabel', '797 College St, Toronto, ON', 'Spanish', TRUE, FALSE, FALSE),
    ('Volos Greek Cuisine', '133 Richmond St W, Toronto, ON', 'Greek', TRUE, FALSE, FALSE),
    ('Barque Smokehouse Roncesvalles', '299 Roncesvalles Ave, Toronto, ON', 'BBQ', TRUE, FALSE, FALSE);

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
