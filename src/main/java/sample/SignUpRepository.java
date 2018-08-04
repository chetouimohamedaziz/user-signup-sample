package sample;

import org.springframework.data.repository.CrudRepository;

interface SignUpRepository extends CrudRepository<User, String> {
}
