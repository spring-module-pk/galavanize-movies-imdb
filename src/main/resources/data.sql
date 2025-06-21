INSERT INTO movies (title, director, actors, release, description, rating, created_date, is_Trending)
VALUES ('The Avengers', 'Joss Whedon', 'Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth', 2012,
        'Earth''s mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.',
        4.2, CURRENT_TIMESTAMP, true);

INSERT INTO movies (title, director, actors, release, description, rating, created_date, is_Trending)
VALUES ('Superman Returns', 'Bryan Singer', 'Brandon Routh, Kate Bosworth, Kevin Spacey, James Marsden', 2006,
        'Superman returns to Earth after spending five years in space examining his homeworld Krypton. But he finds things have changed while he was gone, and he must once again prove himself important to the world.',
        2.9, CURRENT_TIMESTAMP, true);

INSERT INTO movies (title, director, actors, release, description, rating, created_date)
VALUES ('Steel', 'Kenneth Johnson', 'Shaquille O''Neal, Annabeth Gish, Judd Nelson, Richard Roundtree', 1997,
        'A scientist for the military turns himself into a cartoon-like superhero when a version of one of his own weapons is being used against enemies.',
        2.1, CURRENT_TIMESTAMP);

INSERT INTO movies (title, director, actors, release, description, rating, created_date)
VALUES ('Unbreakable', 'M. Night Shyamalan', 'Bruce Willis, Samuel L. Jackson, Robin Wright, Spencer Treat Clark', 2000,
        'A man learns something extraordinary about himself after a devastating accident.',
        5.0, CURRENT_TIMESTAMP);

INSERT INTO movies (title, director, actors, release, description, rating, created_date)
VALUES ('Rocketeer', 'Jay Light', 'Christopher Coakley', 2012,
        'N/A', 4.0, CURRENT_TIMESTAMP);

INSERT INTO movies (title, director, actors, release, description, rating, created_date)
VALUES ('The Incredibles', 'Brad Bird', 'Craig T. Nelson, Holly Hunter, Samuel L. Jackson, Jason Lee', 2004,
        'A family of undercover superherōoes, while trying to live the quiet suburban life, are forced into action to save the world.',
        3.8, CURRENT_TIMESTAMP);

INSERT INTO movies (title, director, actors, release, description, rating, created_date)
VALUES ('The Lego Batman Movie', 'Chris McKay', 'Will Arnett, Michael Cera, Rosario Dawson, Ralph Fiennes', 2017,
        'A cooler-than-ever Bruce Wayne must deal with the usual suspects as they plan to rule Gotham City, while discovering that he has accidentally adopted a teenage orphan who wishes to become his sidekick.',
        5.0, CURRENT_TIMESTAMP);

INSERT INTO movies (title, director, actors, release, description, rating, created_date, is_Trending)
VALUES ('The Avengers', 'Joss Whedon', 'Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth', 2012,
        'Heroes unite against Loki.', 4.2, CURRENT_TIMESTAMP, true),
       ('Age of Ultron', 'Joss Whedon', 'Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth', 2015,
        'Ultron threatens humanity.', 4.0, CURRENT_TIMESTAMP, true),
       ('Buffy the Vampire Slayer', 'Joss Whedon', 'Kristy Swanson, Donald Sutherland', 1992,
        'A teenage girl fights vampires.', 3.5, CURRENT_TIMESTAMP, false),
       ('Firefly', 'Joss Whedon', 'Nathan Fillion, Gina Torres', 2002, 'A space western adventure.', 4.8,
        CURRENT_TIMESTAMP, false),
       ('Serenity', 'Joss Whedon', 'Nathan Fillion, Gina Torres', 2005, 'Based on Firefly series.', 4.5,
        CURRENT_TIMESTAMP, true),
       ('Cabin in the Woods', 'Joss Whedon', 'Kristen Connolly, Chris Hemsworth', 2011, 'A horror story with a twist.',
        4.0, CURRENT_TIMESTAMP, false),
       ('Much Ado About Nothing', 'Joss Whedon', 'Amy Acker, Alexis Denisof', 2012, 'A modern Shakespeare adaptation.',
        4.1, CURRENT_TIMESTAMP, false),
       ('Dr Horribles Sing Along Blog', 'Joss Whedon', 'Neil Patrick Harris, Nathan Fillion', 2008,
        'A villain struggles with love.', 4.6, CURRENT_TIMESTAMP, true),
       ('Justice League Dark', 'Joss Whedon', 'Matt Ryan, Jason OMara', 2017, 'A team battles supernatural threats.',
        3.9, CURRENT_TIMESTAMP, false),
       ('Alien Resurrection', 'Joss Whedon', 'Sigourney Weaver, Winona Ryder', 1997, 'Ripley returns to fight aliens.',
        3.7, CURRENT_TIMESTAMP, false);

-- Bryan Singer
INSERT INTO movies (title, director, actors, release, description, rating, created_date, is_Trending)
VALUES ('Superman Returns', 'Bryan Singer', 'Brandon Routh, Kate Bosworth', 2006, 'Superman returns to Earth.', 2.9,
        CURRENT_TIMESTAMP, true),
       ('X Men', 'Bryan Singer', 'Hugh Jackman, Patrick Stewart', 2000, 'Mutants fight for their rights.', 4.3,
        CURRENT_TIMESTAMP, true),
       ('X2 X Men United', 'Bryan Singer', 'Hugh Jackman, Ian McKellen', 2003, 'Mutants face a new threat.', 4.5,
        CURRENT_TIMESTAMP, true),
       ('X Men Days of Future Past', 'Bryan Singer', 'James McAvoy, Michael Fassbender', 2014,
        'Time traveling mutants.', 4.7, CURRENT_TIMESTAMP, true),
       ('Valkyrie', 'Bryan Singer', 'Tom Cruise, Bill Nighy', 2008, 'A plot to assassinate Hitler.', 4.2,
        CURRENT_TIMESTAMP, false),
       ('Apt Pupil', 'Bryan Singer', 'Ian McKellen, Brad Renfro', 1998, 'A dark psychological thriller.', 3.8,
        CURRENT_TIMESTAMP, false),
       ('Jack the Giant Slayer', 'Bryan Singer', 'Nicholas Hoult, Ewan McGregor', 2013, 'A giant battle unfolds.', 3.6,
        CURRENT_TIMESTAMP, false),
       ('The Usual Suspects', 'Bryan Singer', 'Kevin Spacey, Gabriel Byrne', 1995, 'A thrilling mystery unfolds.', 4.9,
        CURRENT_TIMESTAMP, true),
       ('Public Access', 'Bryan Singer', 'Ron Marquette, Leigh Hunt', 1993, 'A local television show.', 3.5,
        CURRENT_TIMESTAMP, false),
       ('Bohemian Rhapsody', 'Bryan Singer', 'Rami Malek, Lucy Boynton', 2018, 'A biopic about Freddie Mercury.', 4.7,
        CURRENT_TIMESTAMP, true);

-- Kenneth Johnson
INSERT INTO movies (title, director, actors, release, description, rating, created_date, is_Trending)
VALUES ('Steel', 'Kenneth Johnson', 'Shaquille O Neal, Annabeth Gish', 1997, 'A scientist becomes a superhero.', 2.1,
        CURRENT_TIMESTAMP, false),
       ('V The Mini Series', 'Kenneth Johnson', 'Jane Badler, Marc Singer', 1983, 'Aliens arrive on Earth.', 4.5,
        CURRENT_TIMESTAMP, false),
       ('Short Circuit 2', 'Kenneth Johnson', 'Fisher Stevens, Michael McKean', 1988, 'A robot faces challenges.', 3.7,
        CURRENT_TIMESTAMP, false),
       ('Alien Nation', 'Kenneth Johnson', 'James Caan, Mandy Patinkin', 1988, 'Humans and aliens coexist.', 4.2,
        CURRENT_TIMESTAMP, false),
       ('The Incredible Hulk', 'Kenneth Johnson', 'Bill Bixby, Lou Ferrigno', 1978, 'A scientist transforms into Hulk.',
        4.3, CURRENT_TIMESTAMP, false),
       ('The Bionic Woman', 'Kenneth Johnson', 'Lindsay Wagner, Richard Anderson', 1976,
        'A woman gains super strength.', 4.0, CURRENT_TIMESTAMP, false),
       ('Deadly Messages', 'Kenneth Johnson', 'Kathleen Beller, Michael Brandon', 1985, 'A psychological thriller.',
        3.5, CURRENT_TIMESTAMP, false),
       ('The Bionic Woman Returns', 'Kenneth Johnson', 'Lindsay Wagner, Lee Majors', 1989, 'The return of an icon.',
        3.8, CURRENT_TIMESTAMP, false),
       ('Bigfoot and Wildboy', 'Kenneth Johnson', 'Ray Young, Joseph Butcher', 1977, 'A wild adventure unfolds.', 3.0,
        CURRENT_TIMESTAMP, false),
       ('The Magic Quilt', 'Kenneth Johnson', 'Various Artists', 1998, 'A fantasy adventure.', 2.8, CURRENT_TIMESTAMP,
        false);

