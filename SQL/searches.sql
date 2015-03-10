/* Search Engine Queries */
/* Part 1 Name as Input */

SELECT	p.name, l.licence_no, p.addr, p.birthday, l.class, c.description, l.expiring_date
FROM	people p, drive_licence l, restriction r, driving_condition c
WHERE	p.sin			=	l.sin			AND
		r.licence_no	=	l.licence_no	AND
		r.r_id			=	c.c_id			AND
		p.name			=	INPUT;

/* Part 1 Licence No as Input */

SELECT	p.name, l.licence_no, p.addr, p.birthday, l.class, c.description, l.expiring_date
FROM	people p, drive_licence l, restriction r, driving_condition c
WHERE	p.sin			=	l.sin			AND
		r.licence_no	=	l.licence_no	AND
		r.r_id			=	c.c_id			AND
		l.licence_no	=	INPUT;

/* Part 2 Sin as Input */

SELECT	ticket_no
FROM	ticket
WHERE	violator_no	=	INPUT;

/* Part 2 Licence No as Input */

SELECT	t.ticket_no
FROM	ticket t, people p, drive_licence l
WHERE	t.violator_no	=	p.sin	AND
		p.sin			=	l.sin	AND
		l.licence_no	=	INPUT;

/* Part 3 */

SELECT		v.serial_no, COUNT(s.transaction_id) AS "Sales", AVG(s.price) AS "Price", COUNT(t.ticket_no) AS "Violations
FROM		auto_sale s, vehicle v, ticket t
WHERE		s.vehicle_id	=	v.serial_no		AND
			v.serial_no		=	t.vehicle_id	AND
			v.serial_no		=	INPUT
GROUP BY 	v.serial_no;