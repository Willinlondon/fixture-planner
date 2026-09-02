package com.willinlondon.fixture_planner.fixture;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FixtureRepository extends JpaRepository<Fixture, UUID> {
}
