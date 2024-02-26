BEGIN TRANSACTION;

INSERT INTO itineraries (user_id, itinerary_name, starting_address)
VALUES (1,
        'Exploring Cincinnati',
        '337 West Mcmillan Street, Cincinnati, OH 45219, United States of America'
        );

INSERT INTO itineraries (user_id, itinerary_name, starting_address)
VALUES (1,
        'Summer Trip',
        '3400 Vine Street, Cincinnati, OH 45268, United States of America'
        );

INSERT INTO itineraries (user_id, itinerary_name, starting_address)
VALUES (1,
        'Vacation!',
        '880 Lafayette Avenue, Cincinnati, OH 45220, United States of America'
        );

COMMIT TRANSACTION;