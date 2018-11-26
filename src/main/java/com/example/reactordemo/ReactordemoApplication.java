package com.example.reactordemo;

import com.example.reactordemo.model.Person;
import com.example.reactordemo.model.PersonKey;
import com.example.reactordemo.repository.PersonRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@EnableCassandraRepositories
@SpringBootApplication
public class ReactordemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactordemoApplication.class, args);
	}
	@Autowired
	private PersonRepository personRepository;

	@Component
	public class CommandLineAppStartupRunner implements CommandLineRunner {

		@Override
		public void run(String... args) throws Exception {
			personRepository.deleteAll().subscribe();
			final Person a = new Person(new PersonKey("John", LocalDateTime.now(), UUID.randomUUID()), "A", 1000);
			final Person d = new Person(new PersonKey("Not John", LocalDateTime.now(), UUID.randomUUID()), "D", 1000);
			final Person c = new Person(new PersonKey("John1", LocalDateTime.now(), UUID.randomUUID()), "C", 1006);
			//personRepository.insert(List.of(a, d)).subscribe();
			System.out.println("starting findAll");
			personRepository.findAll().log().map(Person::getLastName).subscribe(l -> System.out.println("findAll: " + l));
			System.out.println("starting findByKeyFirstName");
			personRepository.findByKeyFirstName("John").log().map(Person::getLastName).subscribe(l -> System.out.println("findByKeyFirstName: " + l));
			System.out.println("starting findOneByKeyFirstName");
			Flux<Long> l= Flux.interval(Duration.ofSeconds(5));
			personRepository.findAll().zipWith(l).map(obj->obj.getT1().getLastName()).subscribe(System.out::println);
			Flux.just(1,null).subscribe(v->sum(v),v->error(v));
			Flux.empty().switchIfEmpty(test()).subscribe(m->System.out.println("pooja1 "+m));
			Flux.just(1,2).doOnNext(v -> test()).subscribe(m->System.out.println("pooja2 "+m));
			personRepository.save(c).doOnSuccess(v->test()).subscribe(m->System.out.println("pooja3 "+m.getLastName()));
		}
		private void sum(int a){ System.out.println("sum is " + (1 + a));}

		private void error(Throwable e){
			System.out.println("pooja "+e);
		}
		private Mono<Integer> test(){
			return Mono.just(3);
		}
	}
}