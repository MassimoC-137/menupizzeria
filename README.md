Questo programma di JAVA utilizza un un'interfaccia utente collegata al database che tramite input da terminale permette di: 
1. Dare tutte le pizze del menù. 
2. Dare una sola pizza. 
3. Cambiare la disponibilità degli ingredienti. 
4. Creare una nuova pizza. 
5. Aggiornare una pizza già esistente. 
6. Eliminare una pizza dal menu.

Se si vuole, utilizzare i seguenti codici per creare un database chiamato "menupizzeriadb" con delle pizze, degli ingredienti e la relazione tra gli ingredienti e le pizze: 



CREA PIZZE: 

———

SELECT * FROM menupizzeriadb.pizza;

INSERT INTO menupizzeriadb.pizza (nome, prezzo, gourmet) VALUES
('Americana', 8.50, 0),
('Boscaiola', 7.50, 0),
('Capricciosa', 9.00, 0),
('Diavola', 8.00, 0),
('Funghi', 7.00, 0),
('Margherita', 6.50, 0),
('Marinara', 6.00, 0),
('Mortadella e pesto di pistacchi', 9.50, 0),
('Ortolana', 8.50, 0),
('Quattro formaggi', 8.50, 0),
('Quattro stagioni', 9.00, 0),
('Tonno', 8.00, 0);

———

CREA INGREDIENTI 

———

SELECT * FROM menupizzeriadb.ingrediente;

INSERT INTO menupizzeriadb.ingrediente (nome, disponibilita) VALUES
('Pomodoro', 1),
('Mozzarella', 1),
('Würstel', 1),
('Patatine fritte', 1),
('Funghi', 1),
('Salsiccia', 1),
('Prosciutto cotto', 1),
('Olive nere', 1),
('Salame piccante', 1),
('Peperoncino', 1),
('Prezzemolo', 1),
('Olio', 1),
('Basilico', 1),
('Aglio', 1),
('Origano', 1),
('Mortadella', 1),
('Pesto di pistacchi', 1),
('Peperoni', 1),
('Melanzane', 1),
('Zucchine', 1),
('Verdure grigliate varie', 1),
('Gorgonzola', 1),
('Fontina', 1),
('Parmigiano', 1),
('Carciofi', 1),
('Tonno', 1),
('Cipolla', 1);


COLLEGA INGREDIENTI A PIZZE

———

CREATE TABLE `menupizzeriadb`.`ingredienti_pizza` (
  `id_pizza` int NOT NULL,
  `id_ingredienti` int NOT NULL,
  KEY `id_pizza_fk_idx` (`id_pizza`),
  KEY `id_ingrediente_fk_idx` (`id_ingredienti`),
  CONSTRAINT `id_pizza_fk` FOREIGN KEY (`id_pizza`) REFERENCES `pizza` (`id`),
  CONSTRAINT `id_ingrediente_fk` FOREIGN KEY (`id_ingredienti`) REFERENCES `ingrediente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


SELECT * FROM menupizzeriadb.ingredienti_pizza;

INSERT INTO menupizzeriadb.ingredienti_pizza (id_pizza, id_ingrediente) VALUES
-- Americana
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Americana'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Pomodoro'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Americana'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Mozzarella'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Americana'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Würstel'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Americana'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Patatine fritte'LIMIT 1)),

-- Boscaiola
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Boscaiola'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Mozzarella'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Boscaiola'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Funghi'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Boscaiola'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Salsiccia'LIMIT 1)),

-- Capricciosa
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Capricciosa'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Pomodoro'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Capricciosa'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Mozzarella'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Capricciosa'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Funghi'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Capricciosa'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Carciofi'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Capricciosa'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Prosciutto cotto'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Capricciosa'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Olive nere'LIMIT 1)),

-- Diavola
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Diavola'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Pomodoro'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Diavola'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Mozzarella'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Diavola'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Salame piccante'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Diavola'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Peperoncino'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Diavola'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Olive nere'LIMIT 1)),

-- Funghi
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Funghi'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Pomodoro'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Funghi'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Mozzarella'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Funghi'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Funghi'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Funghi'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Prezzemolo'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Funghi'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Olio'LIMIT 1)),

-- Margherita
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Margherita'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Pomodoro'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Margherita'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Mozzarella'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Margherita'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Basilico'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Margherita'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Olio'LIMIT 1)),

-- Marinara
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Marinara'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Pomodoro'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Marinara'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Aglio'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Marinara'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Origano'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Marinara'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Olio'LIMIT 1)),

-- Mortadella e pesto di pistacchi
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Mortadella e pesto di pistacchi'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Mozzarella'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Mortadella e pesto di pistacchi'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Mortadella'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Mortadella e pesto di pistacchi'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Pesto di pistacchi'LIMIT 1)),

-- Ortolana
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Ortolana'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Pomodoro'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Ortolana'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Mozzarella'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Ortolana'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Peperoni'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Ortolana'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Melanzane'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Ortolana'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Zucchine'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Ortolana'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Verdure grigliate varie'LIMIT 1)),

-- Quattro formaggi
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Quattro formaggi'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Mozzarella'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Quattro formaggi'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Gorgonzola'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Quattro formaggi'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Fontina'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Quattro formaggi'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Parmigiano'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Quattro formaggi'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Basilico'LIMIT 1)),

-- Quattro stagioni
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Quattro stagioni'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Pomodoro'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Quattro stagioni'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Mozzarella'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Quattro stagioni'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Funghi'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Quattro stagioni'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Carciofi'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Quattro stagioni'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Prosciutto cotto'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Quattro stagioni'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Olive nere'LIMIT 1)),

-- Tonno
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Tonno'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Pomodoro'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Tonno'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Mozzarella'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Tonno'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Tonno'LIMIT 1)),
((SELECT id FROM menupizzeriadb.pizza WHERE nome = 'Tonno'LIMIT 1), (SELECT id FROM menupizzeriadb.ingrediente WHERE nome = 'Cipolla'LIMIT 1));

———
