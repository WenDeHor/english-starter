package com.example.englishstarter.service;

import com.example.englishstarter.model.Word;
import com.example.englishstarter.repository.WordRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class WorldRepositoryImpl implements WordRepository {
    private WordRepository wordRepository;

    @Override
    public List<Word> findAll() {
        return wordRepository.findAll();
    }

    @Override
    public List<Word> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Word> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Word> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Word entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Word> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Word> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Word> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Word> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Word> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Word> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Word> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Word getOne(Integer integer) {
        return null;
    }

    @Override
    public Word getById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Word> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Word> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Word> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Word> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Word> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Word> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Word, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
