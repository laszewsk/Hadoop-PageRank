package indiana.cgl.hadoop.pagerank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.lang.StringBuffer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Reducer;
 
public class PageRankReduce extends Reducer<LongWritable, Text, LongWritable, Text>{
	public void reduce(LongWritable key, Iterable<Text> values,
			Context context) throws IOException, InterruptedException {
		double sumOfRankValues = 0.0;
		String targetUrlsList = "";
		
		int sourceUrl = (int)key.get();
		int numUrls = context.getConfiguration().getInt("numUrls",1);
		
		//hints each tuple may include: rank value tuple or link relation tuple  
		for (Text value: values){
			String[] strArray = value.toString().split("#"); // CHECK THAT THIS WORKS FOR ALL RELEVANT STRINGS 
			if (strArray.length == 1){ // no outUrls, only PR for this url. Separate length == 1 case necessary? 
				/*Write your code here*/
				sumOfRankValues += Double.parseDouble(strArray[0]);
			}
			else{
				/*Write your code here*/
				sumOfRankValues += Double.parseDouble(strArray[0]);
				for (int i = 1; i < strArray.length; i++) {
					targetUrlsList += "#" + strArray[i];
				}
			}
			/*Write your code here*/

		} // end for loop
		sumOfRankValues = 0.85*sumOfRankValues+0.15*(1.0)/(double)numUrls;
		context.write(key, new Text(sumOfRankValues+targetUrlsList));
	}
}