A micronaut app that tests the handling of updates to a many to many relationship with R2DBC

Unless you use some sort of cascade option nothing happens i.e. the join table is not updated alone.

When you add a cascade option you have to ensure the full related object is present otherwise you'll get non null constraints or worse still if you don't have not null constraints, deleted field values.