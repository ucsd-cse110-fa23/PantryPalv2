package pantrypal;


import java.util.ArrayList;
import java.util.List;

// class to mock the frontend behavior of the sort dropdown and filter buttons
public class MockSortFilterLogic {
    ArrayList<RecipeData> recipes;
    ArrayList<RecipeData> currentRecipes;
    boolean dinYes,lunchYes,breakYes;
    String currentSort;
    
    public MockSortFilterLogic(ArrayList<RecipeData> recipes) {
        this.recipes = recipes; // mock list of recipes
        this.currentRecipes = recipes;
        currentSort = "";
        dinYes = lunchYes = breakYes = true;
    }

    /**
     * Update recipe list order
     * 
     * @param value the way the list should be ordered
     */
    public void updateList(String value) {
        if (value.equals("Alphabetically")) {
            // sort alphabetical
            this.currentRecipes = SortRecipes.sortAlphabetically(this.recipes, false);
        } 
        else if (value.equals("Reverse Alphabetically")) {
            // sort alphabetical
            this.currentRecipes = SortRecipes.sortAlphabetically(this.recipes, true);
        } 
        else if (value.equals("Newest First")) {
            // sort recipes reverse chronological
            this.currentRecipes = SortRecipes.sortByTime(this.recipes, true);
        } else if (value.equals("Oldest First")) {
            // sort recipes chronological
            this.currentRecipes = SortRecipes.sortByTime(this.recipes, false);
        } else {
            // default
            this.currentRecipes = SortRecipes.sortByTime(this.recipes, false);
        }
        this.currentSort = value;
    }

    public void toggleTag(String value) {
        if (value.equals("Breakfast")) {
            breakYes = !breakYes;
            this.currentRecipes = FilterRecipes.filterRecipeByMeal(this.recipes,this.getTags());

        } 
        else if (value.equals("Lunch")) {
            lunchYes = !lunchYes;
            this.currentRecipes = FilterRecipes.filterRecipeByMeal(this.recipes,this.getTags());
        } 
        else if (value.equals("Dinner")) {
            dinYes = !dinYes;
            this.currentRecipes = FilterRecipes.filterRecipeByMeal(this.recipes,this.getTags());
        } 
    }

    /** returns a list of the current meal tags requested
     * @return the meals that were requested 
     */
    public List<String> getTags() {
        List<String> tags = new ArrayList<String>();
        if(dinYes)
            tags.add("Dinner");
        if(lunchYes)
            tags.add("Lunch");
        if(breakYes)
            tags.add("Breakfast");
        return tags;
    }

}


