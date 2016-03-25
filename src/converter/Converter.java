package converter;

import util.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Converter {

    private ArrayList<ColorGroup> groups;
    private ArrayList<Color> colors;
    private ArrayList<Color> centers;

    public Converter(int groupCount, ArrayList<Color> colors) {
        this.groups = new ArrayList<>();
        this.colors = colors;
        this.centers = new ArrayList<>();

        createGroups(groupCount);
        assignColors();
        calculateCenters();
        createNewColorGroups();
    }

    private void createGroups(int groupCount) {
        for (int i = 0; i < groupCount; i++) {
            groups.add(new ColorGroup());
        }
    }

    private void assignColors() {
        for (ColorGroup group : groups) {
            int color = (int) (Math.random() * colors.size());
            group.add(colors.get(color));
        }

    }

    private void calculateCenters() {
        groups.forEach(ColorGroup::calculateCenter);

        centers.clear();
        centers.addAll(groups.stream().map(ColorGroup::getCenter).collect(Collectors.toList()));
    }


    private void createNewColorGroups() {
        groups.forEach(ColorGroup::clear);

        for (Color color : colors) {
            ArrayList<Double> distances = new ArrayList<>();
            for (Color center : centers) {
                distances.add(Utils.getColorDistance(color, center));
            }

            int minIndex = distances.indexOf(Collections.min(distances));
            groups.get(minIndex).add(color);
        }
    }

    public ArrayList<Color> getCenters() {
        return centers;
    }

}