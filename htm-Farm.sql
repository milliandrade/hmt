CREATE TABLE farm {
	farm_name			varchar(100) not null,
	farm_description 	varchar(500),
	
	PRIMARY KEY(farm_name)
};

CREATE TABLE ox {
	ox_id		varchar(16) not null,
	ox_name		varchar(20) not null,
	ox_birth	timestamp 	not null,
	ox_breed	varchar(16) not null,
	ox_mom		varchar(16),
	ox_dad		varchar(16),
	ox_weight	double		not null,
	
	PRIMARY KEY(ox_id)
};

CREATE TABLE waypoints {
	map_id		varchar(16)	not null,
	map_name	varchar(20)	not null,
	map_minX	double		not null,
	map_maxX	double		not null,
	map_minY	double		not null,
	map_maxY	double		not null,
	
	PRIMARY KEY (map_id)
};

CREATE TABLE graze {
	graze_id			varchar(16)	not null,
	graze_date			date	not null,
	graze_intial_hour	time	not null,
	graze_final_hour	time	not null,
	grace_alert			boolean	not null,
	
	PRIMARY KEY (graze_id)
};

