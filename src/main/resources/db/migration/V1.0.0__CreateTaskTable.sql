CREATE TABLE Task (
    id uuid primary key,
    ownerId uuid not null,
    title varchar(64) not null,
    description varchar(255) not null,
    createdAt timestamptz not null,
    completedAt timestamptz,
    deletedAt timestamptz
);

CREATE INDEX task_ownerid ON Task(ownerId);
