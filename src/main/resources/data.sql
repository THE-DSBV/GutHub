INSERT INTO recipe (name, ingredients, instructions, gluten_free) 
VALUES ('Test', 'test Ingredients', 'test instructions', FALSE);

INSERT INTO restaurant (name, location, cuisine_type, gluten_free, featured, celiac_certified)
VALUES ('Happy Hogan & Friends', 'Afghanistan', 'iron', TRUE, TRUE, TRUE);


/*commented out so other stuff cna be tested
INSERT INTO reviews (restaurant_id, user_id, rating, comment)
VALUES (1, 1, 10, 'I loved the food and experience. Did not expect to meet Tony Stark!');

INSERT INTO menu_items (restaurant_id, item_name, celiac_certified, description, restaurant)
(1, 'sloppy tony', TRUE, "The pinacle of joy! A perfectly curated combination of stark industries' finest donuts and our famous dirty milk bomb.", 'Happy Hogan & Friends')

INSERT INTO meals (id, name, type, date)
VALUES (1, 'Ragnars mead', 3, '793-03-08')

INSERT INTO users (username, password_hash, email, year_joined)
VALUES ('john_doe', 'hello-johnDoe:)', 'john@Doe.com', 2026);
*/
