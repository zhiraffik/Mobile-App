package com.example.lr9.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeRepository {

    public static List<Recipe> getAll() {
        List<Recipe> list = new ArrayList<>();

        // Завтрак (Italian)
        list.add(new Recipe(
                "br_omelette",
                Recipe.MealType.BREAKFAST,
                Recipe.Cuisine.ITALIAN,
                "Омлет с помидорами и сыром",
                "Tomato & Cheese Omelette",
                "Tortilla con tomate y queso",
                Arrays.asList("Яйца — 2 шт.", "Помидор — 1 шт.", "Твёрдый сыр — 30 г", "Соль, перец"),
                Arrays.asList("Eggs — 2", "Tomato — 1", "Hard cheese — 30 g", "Salt, pepper"),
                Arrays.asList("Huevos — 2", "Tomate — 1", "Queso curado — 30 g", "Sal, pimienta"),
                Arrays.asList(
                        "Взбейте яйца с солью и перцем.",
                        "Нарежьте помидор, натрите сыр.",
                        "Вылейте яйца на сковороду, добавьте начинку.",
                        "Готовьте 3–5 минут до схватывания."
                ),
                Arrays.asList(
                        "Beat eggs with salt and pepper.",
                        "Chop tomato, grate cheese.",
                        "Pour eggs into pan, add filling.",
                        "Cook 3–5 min until set."
                ),
                Arrays.asList(
                        "Bate los huevos con sal y pimienta.",
                        "Corta el tomate, ralla el queso.",
                        "Vierte los huevos en la sartén y añade el relleno.",
                        "Cocina 3–5 min hasta que cuaje."
                )
        ));

        // Обед (Japanese)
        list.add(new Recipe(
                "lu_miso",
                Recipe.MealType.LUNCH,
                Recipe.Cuisine.JAPANESE,
                "Мисо-суп (упрощённый)",
                "Miso Soup (Simple)",
                "Sopa de miso (simple)",
                Arrays.asList("Вода — 500 мл", "Паста мисо — 1–2 ст. л.", "Тофу — 100 г", "Зелёный лук"),
                Arrays.asList("Water — 500 ml", "Miso paste — 1–2 tbsp", "Tofu — 100 g", "Green onion"),
                Arrays.asList("Agua — 500 ml", "Pasta de miso — 1–2 cdas", "Tofu — 100 g", "Cebolleta"),
                Arrays.asList(
                        "Подогрейте воду почти до кипения.",
                        "Растворите мисо в небольшом количестве воды и добавьте в кастрюлю.",
                        "Добавьте кубики тофу, прогрейте 2 минуты (не кипятить).",
                        "Подавайте с зелёным луком."
                ),
                Arrays.asList(
                        "Heat water almost to a boil.",
                        "Dissolve miso in a bit of water and add back.",
                        "Add tofu cubes, warm 2 min (don’t boil).",
                        "Serve with green onion."
                ),
                Arrays.asList(
                        "Calienta el agua casi hasta hervir.",
                        "Disuelve el miso en un poco de agua y agrégalo.",
                        "Añade tofu en cubos y calienta 2 min (sin hervir).",
                        "Sirve con cebolleta."
                )
        ));

        // Ужин (Russian)
        list.add(new Recipe(
                "di_buckwheat",
                Recipe.MealType.DINNER,
                Recipe.Cuisine.RUSSIAN,
                "Гречка с курицей и овощами",
                "Buckwheat with Chicken & Veggies",
                "Trigo sarraceno con pollo y verduras",
                Arrays.asList("Гречка — 150 г", "Курица — 200 г", "Морковь — 1 шт.", "Лук — 1 шт.", "Соль, специи"),
                Arrays.asList("Buckwheat — 150 g", "Chicken — 200 g", "Carrot — 1", "Onion — 1", "Salt, spices"),
                Arrays.asList("Trigo sarraceno — 150 g", "Pollo — 200 g", "Zanahoria — 1", "Cebolla — 1", "Sal, especias"),
                Arrays.asList(
                        "Промойте гречку и отварите до готовности.",
                        "Обжарьте лук и морковь 3–4 минуты.",
                        "Добавьте курицу, готовьте до готовности.",
                        "Смешайте с гречкой, посолите и приправьте."
                ),
                Arrays.asList(
                        "Rinse and cook buckwheat until done.",
                        "Sauté onion and carrot 3–4 min.",
                        "Add chicken and cook through.",
                        "Mix with buckwheat, season."
                ),
                Arrays.asList(
                        "Enjuaga y cocina el trigo sarraceno.",
                        "Sofríe cebolla y zanahoria 3–4 min.",
                        "Añade el pollo y cocina hasta listo.",
                        "Mezcla con el trigo sarraceno y sazona."
                )
        ));

        // Ужин/Обед (American) — Курица KFC
        list.add(new Recipe(
                "di_kfc",
                Recipe.MealType.DINNER,
                Recipe.Cuisine.AMERICAN,
                "Курица КФС",
                "KFC-Style Fried Chicken",
                "Pollo frito estilo KFC",
                Arrays.asList(
                        "Курица",
                        "Мука пшеничная",
                        "Крахмал кукурузный",
                        "Специи (орегано, базилик, тимьян, белый/чёрный перец, паприка, куркума, чеснок и др.)",
                        "Масло для жарки",
                        "Бумажные полотенца",
                        "Яйца (3–6 шт.)"
                ),
                Arrays.asList(
                        "Chicken",
                        "Wheat flour",
                        "Corn starch",
                        "Spices (oregano, basil, thyme, white/black pepper, paprika, turmeric, garlic, etc.)",
                        "Oil for frying",
                        "Paper towels",
                        "Eggs (3–6)"
                ),
                Arrays.asList(
                        "Pollo",
                        "Harina de trigo",
                        "Almidón de maíz",
                        "Especias (orégano, albahaca, tomillo, pimienta blanca/negra, pimentón, cúrcuma, ajo, etc.)",
                        "Aceite para freír",
                        "Toallas de papel",
                        "Huevos (3–6)"
                ),
                Arrays.asList(
                        "Разделайте курицу и замаринуйте. Пример маринада: 1 ст. л. соли + 2 ст. л. сахара на воду.",
                        "Сделайте панировку: 300 г муки + 90 г кукурузного крахмала + 1 ст. л. разрыхлителя + специи.",
                        "Сделайте яичную смесь: 3–6 яиц на 1–1.5 л воды, перемешайте.",
                        "Обваляйте курицу в панировке, затем окуните в яичную смесь (выпустите пузырьки), снова в панировку и сразу во фритюр.",
                        "Время: ноги 12–15 мин; крылья 7–8 мин; филе 5–6 мин. После — на бумажные полотенца."
                ),
                Arrays.asList(
                        "Cut chicken and marinate. Example: 1 tbsp salt + 2 tbsp sugar in water.",
                        "Breading: 300 g flour + 90 g corn starch + 1 tbsp baking powder + spices.",
                        "Egg mix: 3–6 eggs per 1–1.5 L water, whisk.",
                        "Coat in breading, dip into egg mix (let bubbles escape), coat again and fry immediately.",
                        "Time: legs 12–15 min; wings 7–8 min; fillet 5–6 min. Drain on paper towels."
                ),
                Arrays.asList(
                        "Corta el pollo y marínalo. Ejemplo: 1 cda sal + 2 cdas azúcar en agua.",
                        "Empanado: 300 g harina + 90 g almidón de maíz + 1 cda polvo de hornear + especias.",
                        "Mezcla de huevo: 3–6 huevos por 1–1.5 L de agua, bate.",
                        "Reboza, pasa por la mezcla de huevo (que salgan las burbujas), reboza otra vez y fríe enseguida.",
                        "Tiempo: muslos 12–15 min; alas 7–8 min; pechuga 5–6 min. Escurre en papel."
                )
        ));

// Ужин (Italian) — Пицца
        list.add(new Recipe(
                "di_pizza",
                Recipe.MealType.DINNER,
                Recipe.Cuisine.ITALIAN,
                "Пицца (домашняя)",
                "Homemade Pizza",
                "Pizza casera",
                Arrays.asList(
                        "Мука — 520 г",
                        "Вода — 340 г",
                        "Дрожжи сухие — 5 г",
                        "Сахар — 10 г",
                        "Соль — 10 г",
                        "Масло оливковое/растительное — 30 г (+ 10 г растительного по желанию)",
                        "Протёртые томаты — 300 г",
                        "Вода для соуса — ~50 г",
                        "Итальянские травы, чеснок, перец, соль — по вкусу",
                        "Сыр (лучше смесь: твёрдый + моцарелла)",
                        "Топпинги — по вкусу"
                ),
                Arrays.asList(
                        "Flour — 520 g",
                        "Water — 340 g",
                        "Dry yeast — 5 g",
                        "Sugar — 10 g",
                        "Salt — 10 g",
                        "Olive/vegetable oil — 30 g (+ 10 g optional)",
                        "Crushed tomatoes — 300 g",
                        "Water for sauce — ~50 g",
                        "Italian herbs, garlic, pepper, salt — to taste",
                        "Cheese (mix: hard + mozzarella)",
                        "Toppings — to taste"
                ),
                Arrays.asList(
                        "Harina — 520 g",
                        "Agua — 340 g",
                        "Levadura seca — 5 g",
                        "Azúcar — 10 g",
                        "Sal — 10 g",
                        "Aceite de oliva/vegetal — 30 g (+ 10 g opcional)",
                        "Tomate triturado — 300 g",
                        "Agua para salsa — ~50 g",
                        "Hierbas italianas, ajo, pimienta, sal — al gusto",
                        "Queso (mezcla: curado + mozzarella)",
                        "Toppings — al gusto"
                ),
                Arrays.asList(
                        "Тесто: смешайте воду (часть оставьте 30–50 мл), дрожжи и сахар. Добавьте муку, замесите. Затем добавьте оставшуюся воду, соль и масло, вымешайте.",
                        "Оставьте тесто на 40–70 минут при комнатной температуре (ёмкость смазать маслом).",
                        "Разделите на порции ~350 г, сформируйте шарики, уберите в холодильник на 12–24 часа.",
                        "Соус: обжарьте чеснок в масле, добавьте томаты и воду, специи и соль, немного проварите.",
                        "Пицца: достаньте тесто за 1–1.5 часа. Растягивайте руками (можно на манке). Добавьте соус, сыр, топпинги.",
                        "Разогрейте духовку 275–300°C с противнем внутри. Выпекайте 7–10 минут на горячем противне."
                ),
                Arrays.asList(
                        "Dough: mix water (keep 30–50 ml), yeast and sugar. Add flour and knead. Add remaining water, salt and oil, knead well.",
                        "Rest 40–70 min at room temperature (oiled container).",
                        "Divide into ~350 g balls, refrigerate 12–24 hours.",
                        "Sauce: sauté garlic in oil, add tomatoes + water, herbs/pepper/salt, simmer a bit.",
                        "Pizza: take dough out 1–1.5 h before. Stretch by hand (semolina optional). Add sauce, cheese, toppings.",
                        "Bake at 275–300°C on a preheated tray for ~7–10 min."
                ),
                Arrays.asList(
                        "Masa: mezcla agua (reserva 30–50 ml), levadura y azúcar. Añade harina y amasa. Luego añade el agua restante, sal y aceite y amasa bien.",
                        "Deja reposar 40–70 min a temperatura ambiente (recipiente con aceite).",
                        "Divide en bolas de ~350 g y refrigera 12–24 h.",
                        "Salsa: sofríe ajo en aceite, añade tomate + agua, hierbas/pimienta/sal, cocina un poco.",
                        "Pizza: saca la masa 1–1.5 h antes. Estira con las manos (sémola opcional). Añade salsa, queso y toppings.",
                        "Hornea a 275–300°C en bandeja precalentada ~7–10 min."
                )
        ));
        return list;
    }
}