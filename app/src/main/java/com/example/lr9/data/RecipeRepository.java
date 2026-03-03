package com.example.lr9.data;

import com.example.lr9.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeRepository {

    public static List<Recipe> getAll() {
        List<Recipe> list = new ArrayList<>();

        // Завтрак — Омлет (Italian)
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
                ),
                null, null, null,
                null
        ));

        // Обед — Мисо-суп (Japanese)
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
                ),
                null, null, null,
                null
        ));

        // Ужин — Гречка (Russian)
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
                ),
                null, null, null,
                null
        ));

        // Ужин — Курица KFC (American)
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
                        "Разделать курицу и замариновать (маринад любой). Вариант: 1 ст. л. соли + 2 ст. л. сахара + вода.",
                        "Панировка: 300 г муки + 90 г кукурузного крахмала + 1 ст. л. разрыхлителя + специи.",
                        "Яичная смесь: 3–6 яиц на 1–1.5 л воды, перемешать.",
                        "Курицу: панировка → яичная смесь (выпустить пузырьки) → панировка → сразу во фритюр.",
                        "Время: ноги 12–15 мин, крылья 7–8 мин, филе 5–6 мин. После — на бумажное полотенце."
                ),
                Arrays.asList(
                        "Cut chicken and marinate. Example: 1 tbsp salt + 2 tbsp sugar in water.",
                        "Breading: 300 g flour + 90 g corn starch + 1 tbsp baking powder + spices.",
                        "Egg mix: 3–6 eggs per 1–1.5 L water, whisk.",
                        "Chicken: breading → egg mix (let bubbles escape) → breading → fry immediately.",
                        "Time: legs 12–15 min; wings 7–8 min; fillet 5–6 min. Drain on paper towels."
                ),
                Arrays.asList(
                        "Corta el pollo y marínalo. Ejemplo: 1 cda sal + 2 cdas azúcar en agua.",
                        "Empanado: 300 g harina + 90 g almidón de maíz + 1 cda polvo de hornear + especias.",
                        "Mezcla de huevo: 3–6 huevos por 1–1.5 L de agua, bate.",
                        "Pollo: rebozado → huevo (que salgan burbujas) → rebozado → freír.",
                        "Tiempo: muslos 12–15 min; alas 7–8 min; pechuga 5–6 min. Escurre en papel."
                ),
                null, null, null,
                null
        ));

        // Ужин — Пицца (Italian) + озвучка ru/en/es + видео
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
                        "Масло оливковое/растительное — 30 г",
                        "Протёртые томаты — 300 г",
                        "Вода для соуса — ~50 г",
                        "Итальянские травы, чеснок, перец, соль — по вкусу",
                        "Сыр (твёрдый + моцарелла)",
                        "Топпинги — по вкусу"
                ),
                Arrays.asList(
                        "Flour — 520 g",
                        "Water — 340 g",
                        "Dry yeast — 5 g",
                        "Sugar — 10 g",
                        "Salt — 10 g",
                        "Olive/vegetable oil — 30 g",
                        "Crushed tomatoes — 300 g",
                        "Water for sauce — ~50 g",
                        "Italian herbs, garlic, pepper, salt — to taste",
                        "Cheese (hard + mozzarella)",
                        "Toppings — to taste"
                ),
                Arrays.asList(
                        "Harina — 520 g",
                        "Agua — 340 g",
                        "Levadura seca — 5 g",
                        "Azúcar — 10 g",
                        "Sal — 10 g",
                        "Aceite de oliva/vegetal — 30 g",
                        "Tomate triturado — 300 g",
                        "Agua para salsa — ~50 g",
                        "Hierbas italianas, ajo, pimienta, sal — al gusto",
                        "Queso (curado + mozzarella)",
                        "Toppings — al gusto"
                ),
                Arrays.asList(
                        "Тесто: смешайте воду (часть оставьте 30–50 мл), дрожжи и сахар. Добавьте муку, замесите.",
                        "Добавьте оставшуюся воду, соль и масло, вымешайте.",
                        "Оставьте на 40–70 минут при комнатной температуре.",
                        "Разделите на порции ~350 г и уберите на 12–24 часа в холодильник.",
                        "Соус: обжарьте чеснок в масле, добавьте томаты и воду, специи и соль, немного проварите.",
                        "Соберите пиццу и выпекайте 7–10 минут при 275–300°C на горячем противне."
                ),
                Arrays.asList(
                        "Dough: mix water (keep 30–50 ml), yeast and sugar. Add flour and knead.",
                        "Add remaining water, salt and oil, knead well.",
                        "Rest 40–70 minutes at room temperature.",
                        "Divide into ~350 g balls and refrigerate 12–24 hours.",
                        "Sauce: sauté garlic in oil, add tomatoes + water, season, simmer a bit.",
                        "Assemble and bake 7–10 min at 275–300°C on a preheated tray."
                ),
                Arrays.asList(
                        "Masa: mezcla agua (reserva 30–50 ml), levadura y azúcar. Añade harina y amasa.",
                        "Añade el agua restante, sal y aceite; amasa bien.",
                        "Deja reposar 40–70 min a temperatura ambiente.",
                        "Divide en bolas de ~350 g y refrigera 12–24 h.",
                        "Salsa: sofríe ajo en aceite, añade tomate + agua, sazona y cocina un poco.",
                        "Monta y hornea 7–10 min a 275–300°C en bandeja precalentada."
                ),
                R.raw.pizza_ru, R.raw.pizza_en, R.raw.pizza_es,
                R.raw.pizza_video
        ));

        return list;
    }
}