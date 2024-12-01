package com.noah.matdongsan.repository.property;

import com.noah.matdongsan.entity.property.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorites,Long> {
}
