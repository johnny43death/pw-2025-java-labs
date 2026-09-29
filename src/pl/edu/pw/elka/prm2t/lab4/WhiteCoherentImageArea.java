package pl.edu.pw.elka.prm2t.lab4;

import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;
import java.util.List;

/**
 * To find all white coherent areas in the binary image.
 * @author Igor Kutermankiewicz, Kajetan Rosik
 */
public class WhiteCoherentImageArea {
    private final String pathName;
    private final List<Integer> whiteCoherentImageAreas = new ArrayList<>();
    private final int width;
    private final int height;

    WhiteCoherentImageArea(String pathName) throws IOException {
        this.pathName = pathName;
        final int[][] img = ImageTools.readImage(pathName);
        this.height = img.length;
        this.width = img[0].length;

        boolean[][] visited = new boolean[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (img[i][j] != 0 && !visited[i][j]) {
                    int areaSize = findWhiteAreaSize(img, visited, i, j);
                    visited[i][j] = true;
                    whiteCoherentImageAreas.add(areaSize);
                }
            }
        }
    }

    int findWhiteAreaSize(int[][] img, boolean[][] visited, int x, int y) {
        final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        List<Point> queue = new ArrayList<>();
        queue.add(new Point(x, y));
        int areaSize = 1;

        while (!queue.isEmpty()) {
            Point curr = queue.remove(0);
            int currX = curr.x;
            int currY = curr.y;

            for (int i = 0; i < 8; i++) {
                int newX = currX + dx[i];
                int newY = currY + dy[i];

                if (newX >= 0 && newX < img.length &&
                        newY >= 0 && newY < img[0].length &&
                        img[newX][newY] != 0 && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.add(new Point(newX, newY));
                    areaSize++;
                }
            }
        }

        return areaSize;
    }

    /**
     * @return String describing white coherent areas found in the image; string is ready to present to the end user.
     */
    public String asString() {
        StringBuilder sb = new StringBuilder("There ");
        sb.append(switch (whiteCoherentImageAreas.size()) {
            case 0 -> "is no";
            case 1 -> "is one";
            default -> String.format("are %d", whiteCoherentImageAreas.size());
        });
        sb.append(" coherent area").append(whiteCoherentImageAreas.size() > 1 ? "s" : "");
        sb.append(" in the image file ").append(pathName);

        sb.append(String.format("%nImage size: %d x %d = %d pixels",
                width, height, width * height));

        if (whiteCoherentImageAreas.size() == 1) {
            return sb.toString();
        }
        if (whiteCoherentImageAreas.size() < 2) {
            return sb.toString();
        }

        sb.append(String.format("%nCoherent areas sizes: "));
        for (Integer areaSize : whiteCoherentImageAreas) {
            sb.append(areaSize).append(", ");
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    @Override
    public String toString() {
        return asString();
    }

    public static void main(String[] args) throws IOException {
        final var pathName = "src/pl/edu/pw/elka/prm2t/lab4/resource/white.png";

        try {
            System.out.println(new WhiteCoherentImageArea(pathName).asString());
        } catch (IOException ioe) {
            System.out.printf("Error reading image file %s (%s)%n", pathName, ioe);
        }
    }
}