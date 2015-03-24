/* People */

insert into people values('555-666-000',	'Eric Johnson',		1.86,	140,	'Blue',		'Brown',	'5502 17 Street NW, Edmonton, Alberta',		'm',	TO_DATE('01-APR-1967', 'DD-MON-YYYY'));
insert into people values('555-666-001',	'Susan Johnson',	1.54,	62,	'Green',	'Blonde',	'5502 17 Street NW, Edmonton, Alberta',		'f',	TO_DATE('12-JAN-1965', 'DD-MON-YYYY'));
insert into people values('555-666-002',	'Alan Johnson',		1.75,	76,	'Blue',		'Red',		'5502 17 Street NW, Edmonton, Alberta',		'm',	TO_DATE('08-NOV-1994', 'DD-MON-YYYY'));
insert into people values('555-666-003',	'Andrew Springer',	1.68,	72,	'Hazel',	'White',	'123 Main Street, Hardisty, Alberta',		'm',	TO_DATE('22-MAR-1927', 'DD-MON-YYYY'));
insert into people values('555-666-004',	'Trish Hunter',		1.32,	58,	'Green',	'Brown',	'8904 126 Avenue NW, Edmonton, Alberta',	'f',	TO_DATE('04-FEB-1981', 'DD-MON-YYYY'));
insert into people values('555-666-005',	'Michael Parker',	1.79,	95,	'Grey',		'Black',	'1302 10A Street N, Lethbridge, Alberta',	'm',	TO_DATE('02-JUN-1953', 'DD-MON-YYYY'));
insert into people values('555-666-006',	'Sandra Fisher',	1.45,	63,	'Blue',		'Red',		'26 Mulberry Lane, St. Albert, Alberta',	'f',	TO_DATE('25-DEC-1985', 'DD-MON-YYYY'));
insert into people values('555-666-007',	'James Fisher',		1.92,	85,	'Green',	'Black',	'26 Mulberry Lane, St. Albert, Alberta',	'm',	TO_DATE('23-AUG-1987', 'DD-MON-YYYY'));
insert into people values('555-666-008',	'Tim Erikson',		1.77,	78,	'Brown',	'Bald',		'355 Birch Street, Sherwood Park, Alberta',	'm',	TO_DATE('09-APR-1972', 'DD-MON-YYYY'));
insert into people values('555-666-009',	'Lisa Erikson',		1.42,	61,	'Hazel',	'Blonde',	'355 Birch Street, Sherwood Park, Alberta',	'f',	TO_DATE('19-JUL-1991', 'DD-MON-YYYY'));
insert into people values('555-666-010',	'Peter Erikson',	1.81,	81,	'Green',	'Brown',	'355 Birch Street, Sherwood Park, Alberta',	'm',	TO_DATE('09-MAR-1992', 'DD-MON-YYYY'));

/* Driver's Licences */

insert into drive_licence values('38907-2260',	'555-666-000',	'1',		null,	TO_DATE('01-APR-2007', 'DD-MON-YYYY'),	TO_DATE('01-APR-2017', 'DD-MON-YYYY'));
insert into drive_licence values('59715-6013',	'555-666-001',	'5',		null,	TO_DATE('12-JAN-2008', 'DD-MON-YYYY'),	TO_DATE('12-JAN-2018', 'DD-MON-YYYY'));
insert into drive_licence values('32881-4310',	'555-666-002',	'7',		null,	TO_DATE('17-JAN-2010', 'DD-MON-YYYY'),	TO_DATE('08-NOV-2015', 'DD-MON-YYYY'));
insert into drive_licence values('97336-9161',	'555-666-003',	'5',		null,	TO_DATE('22-MAR-1992', 'DD-MON-YYYY'),	TO_DATE('22-MAR-2002', 'DD-MON-YYYY'));
insert into drive_licence values('89423-6107',	'555-666-004',	'5',		null,	TO_DATE('04-FEB-2006', 'DD-MON-YYYY'),	TO_DATE('04-FEB-2016', 'DD-MON-YYYY'));
insert into drive_licence values('17688-2617',	'555-666-005',	'4',		null,	TO_DATE('02-JUN-2014', 'DD-MON-YYYY'),	TO_DATE('02-JUN-2024', 'DD-MON-YYYY'));
insert into drive_licence values('47108-6761',	'555-666-006',	'5',		null,	TO_DATE('25-DEC-2008', 'DD-MON-YYYY'),	TO_DATE('25-DEC-2018', 'DD-MON-YYYY'));
insert into drive_licence values('97168-2113',	'555-666-007',	'5',		null,	TO_DATE('23-AUG-2012', 'DD-MON-YYYY'),	TO_DATE('23-AUG-2022', 'DD-MON-YYYY'));
insert into drive_licence values('36771-9671',	'555-666-008',	'5',		null,	TO_DATE('09-APR-2010', 'DD-MON-YYYY'),	TO_DATE('09-APR-2020', 'DD-MON-YYYY'));
insert into drive_licence values('11053-0167',	'555-666-009',	'nondriving',	null,	TO_DATE('23-JUL-2009', 'DD-MON-YYYY'),	TO_DATE('19-JUL-2019', 'DD-MON-YYYY'));
insert into drive_licence values('42138-2719',	'555-666-010',	'5',		null,	TO_DATE('17-FEB-2011', 'DD-MON-YYYY'),	TO_DATE('09-MAR-2021', 'DD-MON-YYYY'));

/* Driving Conditions */

insert into driving_condition values(0,	'Requires glasses');
insert into driving_condition values(1,	'Can only drive during daylight');
insert into driving_condition values(2,	'Requires licensed adult in the vehicle');
insert into driving_condition values(3,	'No seatbelt required');
insert into driving_condition values(4,	'Air brakes permitted');
insert into driving_condition values(5,	'Motorcycle permit');

/* Restrictions */

insert into restriction values('32881-4310',	2);
insert into restriction values('97336-9161',	0);
insert into restriction values('97336-9161',	1);
insert into restriction values('38907-2260',	3);
insert into restriction values('38907-2260',	4);
insert into restriction values('36771-9671',	0);
insert into restriction values('47108-6761',	0);
insert into restriction values('97168-2113',	5);

/* Vehicle Types */

insert into vehicle_type values(0,	'Coupe');
insert into vehicle_type values(1,	'Sedan');
insert into vehicle_type values(2,	'Hatchback');
insert into vehicle_type values(3,	'Pickup');
insert into vehicle_type values(4,	'SUV');
insert into vehicle_type values(5,	'Motorcycle');
insert into vehicle_type values(6,	'Minivan');

/* Vehicles */

insert into vehicle values('0104-826-01665',	'GMC',		'Envoy',	2004,	'Silver',	4);
insert into vehicle values('0200-216-34861',	'Ford',		'F-150',	2000,	'White',	3);
insert into vehicle values('0207-216-01665',	'Ford',		'F-150',	2007,	'White',	3);
insert into vehicle values('0209-681-46319',	'Ford',		'Excursion',	2009,	'Gold',		4);
insert into vehicle values('0354-447-14582',	'Chevrolet',	'C10',		1954,	'Blue',		3);
insert into vehicle values('0312-714-56122',	'Chevrolet',	'Corvette',	2012,	'Orange',	0);
insert into vehicle values('0471-614-71648',	'Dodge',	'Challenger',	1971,	'Red',		0);
insert into vehicle values('0409-987-26107',	'Dodge',	'RAM 3500',	2009,	'Black',	3);
insert into vehicle values('0581-224-71922',	'Honda',	'Civic',	1981,	'Gold',		2);
insert into vehicle values('0594-281-97116',	'Honda',	'Civic',	1994,	'Pink',		1);
insert into vehicle values('0503-118-22653',	'Honda',	'Pilot',	2003,	'Blue',		4);
insert into vehicle values('0606-315-88916',	'Cadillac',	'Escalade',	2006,	'Silver',	4);
insert into vehicle values('0702-867-46275',	'Mitsubishi',	'Lancer',	2002,	'Red',		1);
insert into vehicle values('0814-021-91627',	'Suzuki',	'GSX-R1000',	2014,	'Green',	5);
insert into vehicle values('0979-826-01665',	'Mazda',	'RX-7',		1979,	'Red',		0);
insert into vehicle values('0992-119-57319',	'Mazda',	'RX-7',		1992,	'Orange',	0);
insert into vehicle values('1114-411-74823',	'Mercedes',	'ML63 AMG',	2014,	'Black',	4);

/* Owners */

insert into owner values('555-666-000',	'0209-681-46319',	'y');
insert into owner values('555-666-000',	'0606-315-88916',	'y');
insert into owner values('555-666-000',	'1114-411-74823',	'y');
insert into owner values('555-666-001',	'0312-714-56122',	'y');
insert into owner values('555-666-002',	'0594-281-97116',	'y');
insert into owner values('555-666-003',	'0354-447-14582',	'y');
insert into owner values('555-666-004',	'0471-614-71648',	'y');
insert into owner values('555-666-005',	'0200-216-34861',	'y');
insert into owner values('555-666-005',	'0207-216-01665',	'y');
insert into owner values('555-666-006',	'0503-118-22653',	'y');
insert into owner values('555-666-006',	'0702-867-46275',	'y');
insert into owner values('555-666-007',	'0814-021-91627',	'y');
insert into owner values('555-666-007',	'0979-826-01665',	'y');
insert into owner values('555-666-007',	'0992-119-57319',	'y');
insert into owner values('555-666-008',	'0409-987-26107',	'y');
insert into owner values('555-666-010',	'0104-826-01665',	'y');
insert into owner values('555-666-010',	'0581-224-71922',	'y');

insert into owner values('555-666-001',	'0209-681-46319',	'n');
insert into owner values('555-666-007',	'0503-118-22653',	'n');
insert into owner values('555-666-007',	'0702-867-46275',	'n');
insert into owner values('555-666-008',	'0104-826-01665',	'n');

/* Sales */

insert into auto_sale values(0,		null,		'555-666-003',	'0354-447-14582', TO_DATE('21-NOV-1953', 'DD-MON-YYYY'),	1100);
insert into auto_sale values(1,		null,		'555-666-003',	'0979-826-01665', TO_DATE('13-APR-1980', 'DD-MON-YYYY'),	8950);
insert into auto_sale values(2,		null,		'555-666-004',	'0471-614-71648', TO_DATE('10-JUN-2000', 'DD-MON-YYYY'),	25000);
insert into auto_sale values(3,		null,		'555-666-007',	'0581-224-71922', TO_DATE('03-SEP-2001', 'DD-MON-YYYY'),	1250);
insert into auto_sale values(4,		null,		'555-666-007',	'0594-281-97116', TO_DATE('03-SEP-2001', 'DD-MON-YYYY'),	1250);
insert into auto_sale values(5,		null,		'555-666-007',	'0702-867-46275', TO_DATE('26-MAR-2003', 'DD-MON-YYYY'),	32725);
insert into auto_sale values(6,		null,		'555-666-006',	'0503-118-22653', TO_DATE('21-OCT-2003', 'DD-MON-YYYY'),	23685);
insert into auto_sale values(7,		null,		'555-666-000',	'0104-826-01665', TO_DATE('11-MAR-2004', 'DD-MON-YYYY'),	54095);
insert into auto_sale values(8,		null,		'555-666-005',	'0200-216-34861', TO_DATE('01-NOV-2004', 'DD-MON-YYYY'),	14500);
insert into auto_sale values(9,		'555-666-007',	'555-666-006',	'0702-867-46275', TO_DATE('20-APR-2007', 'DD-MON-YYYY'),	17250);
insert into auto_sale values(10,	'555-666-003',	'555-666-007',	'0979-826-01665', TO_DATE('27-APR-1987', 'DD-MON-YYYY'),	750);
insert into auto_sale values(11,	null,		'555-666-000',	'0606-315-88916', TO_DATE('08-AUG-2007', 'DD-MON-YYYY'),	78500);
insert into auto_sale values(12,	null,		'555-666-005',	'0207-216-01665', TO_DATE('17-FEB-2008', 'DD-MON-YYYY'),	34545);
insert into auto_sale values(13,	null,		'555-666-008',	'0409-987-26107', TO_DATE('30-MAR-2009', 'DD-MON-YYYY'),	72455);
insert into auto_sale values(14,	null,		'555-666-000',	'0209-681-46319', TO_DATE('22-JUL-2009', 'DD-MON-YYYY'),	64950);
insert into auto_sale values(15,	'555-666-007',	'555-666-002',	'0594-281-97116', TO_DATE('07-JAN-2011', 'DD-MON-YYYY'),	2000);
insert into auto_sale values(16,	'555-666-010',	'555-666-010',	'0104-826-01665', TO_DATE('16-APR-2011', 'DD-MON-YYYY'),	17000);
insert into auto_sale values(17,	'555-666-007',	'555-666-010',	'0581-224-71922', TO_DATE('20-APR-2011', 'DD-MON-YYYY'),	1500);
insert into auto_sale values(18,	null,		'555-666-007',	'0992-119-57319', TO_DATE('16-JUN-2011', 'DD-MON-YYYY'),	11500);
insert into auto_sale values(19,	null,		'555-666-001',	'0312-714-56122', TO_DATE('03-MAY-2012', 'DD-MON-YYYY'),	102500);
insert into auto_sale values(20,	null,		'555-666-007',	'0814-021-91627', TO_DATE('19-JUN-2013', 'DD-MON-YYYY'),	25700);
insert into auto_sale values(21,	null,		'555-666-000',	'1114-411-74823', TO_DATE('17-MAY-2013', 'DD-MON-YYYY'),	89750);

/* Ticket Types */

insert into ticket_type values('Parking',	185);
insert into ticket_type values('Speed 10',	470);
insert into ticket_type values('Speed 30',	780);
insert into ticket_type values('Speed 50',	970);
insert into ticket_type values('Seatbelt',	230);
insert into ticket_type values('Red Light',	520);
insert into ticket_type values('DUI',		840);

/* Tickets */

insert into ticket values(0,	'555-666-007',	'0979-826-01665',	'555-666-001',	'Red Light',	TO_DATE('27-JUL-2014', 'DD-MON-YYYY'),	'St. Albert',		'Red Light Violation');
insert into ticket values(1,	'555-666-005',	'0207-216-01665',	null,		'Speed 10',	TO_DATE('30-AUG-2014', 'DD-MON-YYYY'),	'Lethbridge',		'90 kph in 70 kph zone');
insert into ticket values(2,	'555-666-008',	'0104-826-01665',	'555-666-001',	'Red Light',	TO_DATE('01-SEP-2013', 'DD-MON-YYYY'),	'St. Albert',		'Red Light Violation');
insert into ticket values(3,	'555-666-007',	'0992-119-57319',	'555-666-001',	'Seatbelt',	TO_DATE('04-OCT-2013', 'DD-MON-YYYY'),	'Sherwood Park',	'Passenger no seatbelt');
insert into ticket values(4,	'555-666-004',	'0471-614-71648',	'555-666-001',	'Red Light',	TO_DATE('09-DEC-2013', 'DD-MON-YYYY'),	'Camrose',		'Red Light Violation');
insert into ticket values(5,	'555-666-005',	'0207-216-01665',	'555-666-001',	'Speed 30',	TO_DATE('13-DEC-2013', 'DD-MON-YYYY'),	'Edmonton',		'55 kph in 30 kph zone');
insert into ticket values(6,	'555-666-008',	'0409-987-26107',	'555-666-001',	'Parking',	TO_DATE('22-JAN-2014', 'DD-MON-YYYY'),	'Spruce Grove',		'Parking, Vehicle Towed');
insert into ticket values(7,	'555-666-009',	null		,	'555-666-001',	'DUI',		TO_DATE('11-MAR-2014', 'DD-MON-YYYY'),	'Sherwood Park',	'Biking while intoxicated');
insert into ticket values(8,	'555-666-007',	'0979-826-01665',	'555-666-001',	'Red Light',	TO_DATE('17-MAR-2014', 'DD-MON-YYYY'),	'St. Albert',		'Red Light Violation');
insert into ticket values(9,	'555-666-007',	'0814-021-91627',	'555-666-001',	'Speed 50',	TO_DATE('21-NOV-2014', 'DD-MON-YYYY'),	'Edmonton',		'240 kph in 70 kph zone');