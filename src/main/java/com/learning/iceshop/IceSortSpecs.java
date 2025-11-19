package com.learning.iceshop;

import org.springframework.data.jpa.domain.Specification;

public class IceSortSpecs {

    public static Specification<IceSort> minScore(Integer minScore) {
        if (minScore == null) return null;
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("score"), minScore);
    }

    public static Specification<IceSort> maxScore(Integer maxScore) {
        if (maxScore == null) return null;
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("score"), maxScore);
    }

    public static Specification<IceSort> nameContains(String name) {
        if (name == null || name.isBlank()) return null;
        return (root, query, cb) -> cb.like(root.get("iceSort"), "%" + name + "%");
    }

    public static Specification<IceSort> ingredientContains(String ingredient) {
        if (ingredient == null || ingredient.isBlank()) return null;
        return (root, query, cb) -> cb.isMember(ingredient, root.get("ingredients"));
    }

    public static Specification<IceSort> buildSpec(IceSortFilter f) {
        return Specification.allOf(
                IceSortSpecs.minScore(f.getMinscore()),
                IceSortSpecs.maxScore(f.getMaxscore()),
                IceSortSpecs.nameContains(f.getName()),
                IceSortSpecs.ingredientContains(f.getIngredient())
        );
    }
}