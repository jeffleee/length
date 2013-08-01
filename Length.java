import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author jefflee
 * @Date 2013-08-01
 * @Desc 
 * @version 1.0
 *
 */
public class Length {
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("0.00");
		//get absolutely path of the file
		String classPath = Length.class.getResource("/").getFile();
		File fileInput = new File(classPath + "input.txt");
		File fileOutput = new File(classPath + "output.txt");
		if(!fileOutput.exists()){
			try {
				fileOutput.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		//the mapping of sigular and plural
		Map<String,String> sigularAndPlural = new HashMap<String,String>();
		sigularAndPlural.put("mile", "miles");
		sigularAndPlural.put("yard", "yards");
		sigularAndPlural.put("inch", "inches");
		sigularAndPlural.put("mile", "miles");
		sigularAndPlural.put("foot", "feet");
		sigularAndPlural.put("fath", "faths");
		sigularAndPlural.put("furlong", "furlongs");
		Map<String,Double> convert = new HashMap<String,Double>();
		try {
			fr = new FileReader(fileInput);
			br = new BufferedReader(fr);
			fw = new FileWriter(fileOutput);
			bw = new BufferedWriter(fw);
			//write email address
			bw.write("jiefuly@126.com\n");
			//write blank line
			bw.write("\n");
			String temp;
			int row_count = 0;
			while ((temp = br.readLine()) != null) {
				Double res = 0.0;
				row_count += 1;
				if(row_count < 7){
					String unit[] = temp.split(" ");
					convert.put(unit[1], Double.parseDouble(unit[3]));
					convert.put(sigularAndPlural.get(unit[1]), Double.parseDouble(unit[3]));
				}else if (row_count > 7 && !"".equals(temp)){
					temp = "+ " + temp.trim();
					String unit[] = temp.split(" ");
					//three elements is as group,1st:operator,2nd:the number of length,3rd:length unit
					for (int i = 0; i < unit.length/3 ; i++) {
						if ("+".equals(unit[i * 3])) {
							res += Double.parseDouble(unit[i * 3 + 1])
									* convert.get(unit[i * 3 + 2]);
						} else {
							res -= Double.parseDouble(unit[i * 3 + 1])
									* convert.get(unit[i * 3 + 2]);
						}
					}
					bw.write(df.format(res.doubleValue()) + " m\n");
					bw.flush();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (fr != null) {
					fr.close();
				}
				if (br != null){
					br.close();
				}
				if (fr != null){
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
