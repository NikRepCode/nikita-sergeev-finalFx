
CREATE TABLE IF NOT EXISTS profile_card (
    card_id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    first_name VARCHAR(36) NOT NULL,
    last_name VARCHAR(36) NOT NULL,
    character_crew_member VARCHAR(500),
    marital_status VARCHAR(36) NOT NULL,
    photo_character VARCHAR(255)
);

INSERT INTO profile_card(first_name, last_name, character_crew_member, marital_status, photo_character)
VALUES ('Билли', 'Бонс', 'Он же "Капитан".
Обладатель карты острова
сокровищ. Много пьет и
всегда простужен.
Характер скверный.', 'Не женат.', 'billy_bones.png');

INSERT INTO profile_card(first_name, last_name, character_crew_member, marital_status, photo_character)
VALUES ('Джимми', 'Гокинс', 'Очень, очень хороший мальчик.
Вежлив, правдив, скромен, добр.
Слушает маму, каждое утро
делает зарядку.
Характер очень мягкий.', 'Не женат.', 'jim_hawkins.png');

INSERT INTO profile_card(first_name, last_name, character_crew_member, marital_status, photo_character)
VALUES ('Черный', 'Пес', 'Друг Флинта, охотится за
картой острова сокровищ.
Характер скрытный.', 'Не женат.', 'black_dog.png');

INSERT INTO profile_card(first_name, last_name, character_crew_member, marital_status, photo_character)
VALUES ('Доктор', 'Ливси', 'Очень хороший и веселый
человек
Характер общительный.', 'Не женат.', 'doctor_livesey.png');

INSERT INTO profile_card(first_name, last_name, character_crew_member, marital_status, photo_character)
VALUES ('Слепой', 'Пью', 'Старый пират, друг Флинта.
Жаден, ради денег готов на все.
Характер мерзкий.', 'Не женат.', 'blind_pew.png');

INSERT INTO profile_card(first_name, last_name, character_crew_member, marital_status, photo_character)
VALUES ('Сквайр', 'Трелони', 'Туп, жаден, прожорлив, ленив,
труслив, надменен.
Характер отсутствует.', 'Не женат.', 'squire_trelawney.png');

INSERT INTO profile_card(first_name, last_name, character_crew_member, marital_status, photo_character)
VALUES ('Джон', 'Сильвер', 'Он же "Окорок", он же "Одноногий".
Самый страшный пират, но
удачно притворяется добрым.
Характер скрытный.', 'Не женат.', 'john_silver.png');

INSERT INTO profile_card(first_name, last_name, character_crew_member, marital_status, photo_character)
VALUES ('Капитан' , 'Смоллетт', 'Старый моряк и солдат.
Говорит правду в глаза, отчего
и страдает. Характер прескверный.', 'Не женат.', 'captain_smollett.png');

INSERT INTO profile_card(first_name, last_name, character_crew_member, marital_status, photo_character)
VALUES ('Бен' , 'Ганн', 'В детстве был благовоспитанным
мальчиком, но начал играть в
Орлянку, связался с пиратами
и покатился...
Характер мягкий.', 'Не женат.', 'ben_gunn.png');