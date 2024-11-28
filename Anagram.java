/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram(" Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Mao Riddle","I am L Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  
	// remove spaces!!!
	// check if same length
	// make it while and not for

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		boolean isAnagram = false;

		str1 = preProcessWOSpaces(str1);
		str2 = preProcessWOSpaces(str2);

		if (str1.length() != str2.length()) {
            return false;
		}

		 for (int i = 0; i < str1.length(); i++) {
            char currentChar = str1.charAt(i);
            boolean foundMatch = false;

            for (int j = 0; j < str2.length(); j++) {
                if (str2.charAt(j) == currentChar) {
                    foundMatch = true;
                    str2 = str2.substring(0, j) + ' ' + str2.substring(j + 1);
                    break;
                }
            }

            if (!foundMatch) {
                return false;
            }
        }

        return true;
    }

	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "what no way"
	public static String preProcess(String str) {
		String newStr = "";
		for (int i = 0; i < str.length(); i++){
			int C = str.charAt(i);
		
			if ( C >= 65 && C <= 90) {
				newStr += (char)(C + 32);
			} else if ((C >= 97 && C <= 122) || C == 32) {
				newStr += (char) C; 
			}
		}
		return newStr;
	} 

	public static String preProcessWOSpaces(String str) {
		String newStr = "";
		for (int i = 0; i < str.length(); i++){
			int C = str.charAt(i);
		
			if ( C >= 65 && C <= 90) {
				newStr += (char)(C + 32);
			} else if (C >= 97 && C <= 122) {
				newStr += (char) C; 
			}
		}
		return newStr;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		
		String result = "";

		while (str.length() > 0) {
			int randomIndex = (int) (Math.random() * str.length());
			result += str.charAt(randomIndex);
			str = str.substring(0, randomIndex) + str.substring(randomIndex + 1);
		}
	return result;
	}
}
