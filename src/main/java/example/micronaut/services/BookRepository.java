package example.micronaut.services;

import example.micronaut.domain.Book;
import example.micronaut.domain.Genre;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorPageableRepository;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Join(value = "genres", type = Join.Type.LEFT_FETCH)
@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface BookRepository extends ReactorPageableRepository<Book, UUID> {

    @Transactional
    default Mono<Book> updateGenres(UUID id, Set<Genre> genres) {
        return findById(id).map( book -> {
                book.setGenres(genres);
                return book;
                });
    }
}
