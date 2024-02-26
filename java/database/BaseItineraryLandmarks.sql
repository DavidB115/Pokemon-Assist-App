BEGIN TRANSACTION;

INSERT INTO itinerary_landmarks (itinerary_id, landmark_id)
VALUES (1, 2);

INSERT INTO itinerary_landmarks (itinerary_id, landmark_id)
VALUES (1, 4);

INSERT INTO itinerary_landmarks (itinerary_id, landmark_id)
VALUES (1, 6);

INSERT INTO itinerary_landmarks (itinerary_id, landmark_id)
VALUES (2, 1);

INSERT INTO itinerary_landmarks (itinerary_id, landmark_id)
VALUES (2, 3);

INSERT INTO itinerary_landmarks (itinerary_id, landmark_id)
VALUES (2, 5);

INSERT INTO itinerary_landmarks (itinerary_id, landmark_id)
VALUES (3, 9);

INSERT INTO itinerary_landmarks (itinerary_id, landmark_id)
VALUES (3, 8);

INSERT INTO itinerary_landmarks (itinerary_id, landmark_id)
VALUES (3, 7);

COMMIT TRANSACTION;