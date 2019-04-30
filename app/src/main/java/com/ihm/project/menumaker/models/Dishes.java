package com.ihm.project.menumaker.models;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dishes {
    private static List<Dish> dishes = new ArrayList<>();
    private static List<Dish> dishesEaten = new ArrayList<>();
    private static int pos = 0;


    public static void init (){
        //All dishes existing
        dishes.clear();
        dishes.add(new Dish("Carbonara", R.drawable.carbonara, "Etape 1\n" +
                "Cuire les pâtes dans un grand volume d'eau bouillante salée.\n" +
                "Etape 2\n" +
                "Emincer les oignons et les faire revenir à la poêle. Dès qu'ils ont bien dorés, y ajouter les lardons.\n" +
                "Etape 3\n" +
                "Préparer dans un saladier la crème fraîche, les oeufs, le sel, le poivre et mélanger.\n" +
                "Etape 4\n" +
                "Retirer les lardons du feu dès qu'ils sont dorés et les ajouter à la crème.\n" +
                "Etape 5\n" +
                "Une fois les pâtes cuite al dente, les égoutter et y incorporer la crème. Remettre sur le feu si le plat a refroidi.\n" +
                "Etape 6\n" +
                "Servir et bon appétit !\n" +
                "Vous pouvez également agrémenter votre plat avec des champignons."));
        dishes.add(new Dish("Risotto", R.drawable.risotto, "Etape 1\n" +
                "Il existe sur la toile deux écoles du risotto aux champignons, à savoir celle du champignon qui cuit dans le riz, et celle des champignons poêlés ajoutés à la dernière minute.\n" +
                "Etape 2\n" +
                "Ayant longtemps pratiqué les deux recettes, je souhaitais vous communiquer la mienne :\n" +
                "Etape 3\n" +
                "Séparer les champignons en deux (préférer des cèpes) : une partie servira à élaborer le bouillon et cuira avec le riz. L'autre partie sera poêlé au dernier moment pour la présentation et mettre en avant le champignon tout en conservant une texture ferme.\n" +
                "Etape 4\n" +
                "Faire blondir dans une casserole un demi-oignon émincé dans un mélange de beurre et d'huile d'olive.\n" +
                "Etape 5\n" +
                "Ajouter et poêler les champignons.\n" +
                "Etape 6\n" +
                "Ajouter du bouillon de légume ou de poule (assez pour nourrir le risotto).\n" +
                "Etape 7\n" +
                "Laisser mijoter le bouillon pour qu'il s'imprègne bien du goût et des saveurs des champignons.\n" +
                "Etape 8\n" +
                "Pendant ce temps, émincer un petit peu d'ail, d'échalote et de persil séparément et réserver.\n" +
                "Etape 9\n" +
                "Chauffer une nouvelle casserole (qui accueillera le risotto), ajouter un peu d'huile d'olive et de beurre que vous ferez blondir.\n" +
                "Etape 10\n" +
                "Ajouter le riz carnaroli et remuer jusqu'à le rendre translucide (attention à la température, le riz ne doit pas coller).\n" +
                "Etape 11\n" +
                "Déglacer avec un verre de vin blanc sec.\n" +
                "Etape 12\n" +
                "Continuer à remuer pour que le riz n'adhère pas et laisser réduire l'alcool.\n" +
                "Etape 13\n" +
                "Verser une louche de bouillon que vous avez laissé mijoter et continuer à remuer constamment jusqu'à l'absorption totale du riz.\n" +
                "Etape 14\n" +
                "Réitérer l'étape 10 jusqu'au point de cuisson (préférer al dente).\n" +
                "Etape 15\n" +
                "Juste avant la fin, faire fondre du beurre et un peu d'huile d'olive dans une poêle tout en remuant le risotto.\n" +
                "Etape 16\n" +
                "Faire blondir l'échalote émincée dans la poêle puis ajouter l'ail (attention à ne pas le brûler).\n" +
                "Etape 17\n" +
                "Faire poêler les champignons tout en remuant le risotto, assaisonner et ajouter le persil émincé en fin de cuisson avec un tour de moulin à poivre puis réserver.\n" +
                "Etape 18\n" +
                "Assaisonner le risotto juste avant la fin.\n" +
                "Etape 19\n" +
                "Dresser le riz dans des assiettes creuses. Placer soigneusement sur le haut les champignons poêlés.\n" +
                "Etape 20\n" +
                "Ciseler du persil et parsèment le plat pour la présentation.\n" +
                "Etape 21\n" +
                "Servir avec un tour de moulin à poivre et du parmesan.\n" +
                "Etape 22\n" +
                "Le gras du beurre va s'opposer au salé du parmesan et à l'amertume du cèpe en arrière plan.\n" +
                "Etape 23\n" +
                "Les puristes privilégieront un rouge italien d'âge moyen avec une structure légère mais à la fois ronde et consistante.\n" +
                "Etape 24\n" +
                "Personnellement, je préfère un blanc sec minéral un peu évolué au accent de sous-bois. Un bourgogne fera parfaitement l'affaire.\n" +
                "Etape 25\n" +
                "Bon appétit !"));

        //Dishes eaten
        dishesEaten.clear();
        dishesEaten.add(new Dish("carbonara", R.drawable.carbonara, "blabla"));
        dishesEaten.add(new Dish("risotto", R.drawable.risotto, "blabla"));
        dishesEaten.add(new Dish("risotto", R.drawable.risotto, "blabla"));
        dishesEaten.add(new Dish("risotto", R.drawable.risotto, "blabla"));
    }

    public static void add(Dish dish) {
        dishes.add(dish);
    }

    public static List<Dish> getDishes() {
        return dishes;
    }

    public static List<Dish> getDishesEaten() {
        return dishesEaten;
    }

    public static int size() { return dishes.size();}

    public static Dish getDish(int pos) {
            return dishes.get(pos);
    }

    public static Dish getRandomDish() {
        Random rand = new Random();
        pos = rand.nextInt(dishes.size());
        return dishes.get(pos);
    }
}