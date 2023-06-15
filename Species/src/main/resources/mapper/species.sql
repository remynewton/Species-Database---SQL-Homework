-- More Than 10 Insert Statements
INSERT INTO `species`.`conservation_statuses` (`status`) VALUES ('Vulnerable');

INSERT INTO `species`.`kingdoms` (`name`) VALUES ('Animalia');

INSERT INTO `species`.`classes` (`name`, kingdoms_id) VALUES ('Mammalia', 1);

INSERT INTO `species`.`families` (`name`, classes_id) VALUES ('Felidae', 1);

INSERT INTO `species`.`taxonomies` (kingdoms_id, classes_id, families_id) VALUES (1, 1, 1);

INSERT INTO `species`.`species` (common_name, scientific_name, taxonomies_id, conservation_statuses_id) VALUES ('Tiger', 'Panthera tigris', 1, 1);

INSERT INTO `species`.`habitats` (`name`) VALUES ('Rainforest');

INSERT INTO `species`.`characteristics` (`name`, category) VALUES ('Stripes', 'Fur Pattern');

INSERT INTO `species`.`locations` (`name`, latitude, longitude, habitats_id) VALUES ('India', 28.7041, 77.1025, 1);

INSERT INTO `species`.`habitats` (id, `name`) VALUES ('Taiga');

INSERT INTO `species`.`references` (title, author, `year`) VALUES ('Tigers of the World: Genomics and Conservation', 'Shu-Jin Luo, et al.', 2019);

INSERT INTO `species`.`images` (url, `format`) VALUES ('https://en.wikipedia.org/wiki/Tiger#/media/File:Walking_tiger_female.jpg', 'JPEG');

INSERT INTO `species`.`species_characteristics` (species_id, characteristic_id) VALUES (1, 1);

INSERT INTO `species`.`species_images` (image_id, species_species_id) VALUES (1, 1);

-- 10 Update Statements

UPDATE `species`.`species` SET scientific_name = (SELECT CONCAT(scientific_name, ' tigris') FROM `species`.`species` WHERE species_id = 1) WHERE species_id = 1;

UPDATE `species`.`locations` SET habitats_id = (SELECT id FROM `species`.`habitats` WHERE `name` = "Taiga") WHERE location_id = 1;

UPDATE `species`.`characteristics` SET category = 'Pattern' WHERE `name` = 'Stripes';

UPDATE `species`.`locations` SET habitats_id = (SELECT id FROM `species`.`habitats` WHERE `name` = 'Rainforest') WHERE location_id = 1;

UPDATE `species`.`species` SET common_name = 'Bengal Tiger' WHERE species_id = 1;

UPDATE `species`.`locations` SET latitude = 30.7041, longitude = 78.1025 WHERE `name` = 'India';

UPDATE `species`.`conservation_statuses` SET `status` = 'Endangered' WHERE status_id = 1;

UPDATE `species`.`references` SET author = 'Yue-chen Liu' WHERE reference_id = 1;

UPDATE `species`.`references` SET `year` = 2022 WHERE reference_id = 1;

UPDATE `species`.`references` SET `year` = (SELECT `year` FROM `species`.`references` WHERE `author` = 'Yue-chen Liu') WHERE reference_id = 1;

-- 10 Delete Statements

DELETE FROM `species`.`species_images` WHERE species_species_id = 1;

DELETE FROM `species`.`species_characteristics` WHERE species_id = 1;

DELETE FROM `species`.`images` WHERE image_id = 1;

DELETE FROM `species`.`references` WHERE reference_id = 1;

DELETE FROM `species`.`conservation_statuses` WHERE status = 'Vulnerable';

DELETE FROM `species`.`habitats` WHERE name = 'Taiga';

DELETE FROM `species`.`locations` WHERE name = 'India';

DELETE FROM `species`.`characteristics` WHERE name = 'Stripes';

DELETE FROM `species`.`habitats` WHERE name = 'Rainforest';

DELETE FROM `species`.`taxonomies` WHERE kingdoms_id = 1;

DELETE FROM `species`.`species` WHERE common_name = 'Tiger';

-- Join all tables in the database
SELECT *
FROM species
INNER JOIN taxonomies ON species.taxonomies_id = taxonomies.id
INNER JOIN kingdoms ON taxonomies.kingdoms_id = kingdoms.id
INNER JOIN classes ON taxonomies.classes_id = classes.id
INNER JOIN families ON taxonomies.families_id = families.id
INNER JOIN conservation_statuses ON species.conservation_statuses_id = conservation_statuses.conservation_status_id
INNER JOIN species_characteristics ON species.species_id = species_characteristics.species_id
INNER JOIN characteristics ON species_characteristics.characteristic_id = characteristics.characteristic_id
INNER JOIN species_images ON species.species_id = species_images.species_species_id
INNER JOIN images ON species_images.image_id = images.image_id
INNER JOIN species_locations ON species.species_id = species_locations.species
INNER JOIN locations ON species_locations.location_id = locations.location_id
INNER JOIN habitats ON locations.habitats_id = habitats.habitat_id
INNER JOIN species_references ON species.species_id = species_references.species_id
INNER JOIN `references` ON species_references.reference_id = `references`.reference_id;

-- Left join
SELECT *
FROM species
LEFT JOIN taxonomies ON species.taxonomies_id = taxonomies.id;

-- Right join
SELECT *
FROM species
RIGHT JOIN taxonomies ON species.taxonomies_id = taxonomies.id;

-- Inner join
SELECT *
FROM species
INNER JOIN taxonomies ON species.taxonomies_id = taxonomies.id;

-- Outer join
SELECT *
FROM species
LEFT JOIN taxonomies ON species.taxonomies_id = taxonomies.id
UNION
SELECT *
FROM species
RIGHT JOIN taxonomies ON species.taxonomies_id = taxonomies.id;

-- 7 statements with aggregate functions and GROUP BY without HAVING
-- 1.
SELECT MAX(latitude) AS max_latitude FROM locations;

-- 2.
SELECT MIN(longitude) AS min_longitude FROM locations;

-- 3.
SELECT COUNT(*) AS num_species, taxonomies_id FROM species
GROUP BY taxonomies_id;

-- 4.
SELECT COUNT(*) AS num_species, conservation_statuses_id FROM species
GROUP BY conservation_statuses_id;

-- 5.
SELECT AVG(latitude) AS avg_latitude, habitats_id FROM locations
GROUP BY habitats_id;

-- 6.
SELECT SUM(species_id) AS total_species_id, conservation_statuses_id FROM species
GROUP BY conservation_statuses_id;

-- 7.
SELECT COUNT(*) AS num_species, locations.habitats_id, habitats.name FROM species
JOIN species_locations ON species.species_id = species_locations.species
JOIN locations ON species_locations.location_id = locations.location_id
JOIN habitats ON locations.habitats_id = habitats.habitat_id
GROUP BY locations.habitats_id;

-- 7 statements with aggregate functions and GROUP BY with HAVING
-- 1.
SELECT MAX(latitude) AS max_latitude FROM locations
HAVING max_latitude > 0;

-- 2.
SELECT MIN(longitude) AS min_longitude FROM locations
HAVING min_longitude < 0;

-- 3.
SELECT COUNT(*) AS num_species, taxonomies_id FROM species
GROUP BY taxonomies_id
HAVING num_species > 5;

-- 4.
SELECT COUNT(*) AS num_species, conservation_statuses_id FROM species
GROUP BY conservation_statuses_id
HAVING num_species < 10;

-- 5.
SELECT AVG(latitude) AS avg_latitude, habitats_id FROM locations
GROUP BY habitats_id
HAVING avg_latitude > 0;

-- 6.
SELECT SUM(species_id) AS total_species_id, conservation_statuses_id FROM species
GROUP BY conservation_statuses_id
HAVING total_species_id > 100;

-- 7.
SELECT COUNT(*) AS num_species, locations.habitats_id, habitats.name FROM species
JOIN species_locations ON species.species_id = species_locations.species
JOIN locations ON species_locations.location_id = locations.location_id
JOIN habitats ON locations.habitats_id = habitats.habitat_id
GROUP BY locations.habitats_id
HAVING num_species > 3;

