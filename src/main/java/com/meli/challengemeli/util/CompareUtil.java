package com.meli.challengemeli.util;

import java.util.ArrayList;
import java.util.List;

public class CompareUtil {

	private CompareUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static boolean compareSizeList(List<List<String>> messages) {

		boolean sizeEquals = true;
		for (int i = 0; i < messages.size() - 1; i++) {
			if (messages.get(i).size() != messages.get(i + 1).size()) {
				sizeEquals = false;
			}
		}

		return sizeEquals;

	}

	public static List<String> compareListNodoEqualSize(List<String> base, List<String> list) {

		List<String> nodos = new ArrayList<>();

		for (int i = 0; i < base.size(); i++) {
			if (base.get(i).equals(list.get(i))) {
				nodos.add(base.get(i));
			} else if (base.get(i).equals("") && !list.get(i).equals("")) {
				nodos.add(list.get(i));
			} else if (!base.get(i).equals("") && list.get(i).equals("")) {
				nodos.add(base.get(i));
			}
		}

		return nodos;
	}

	public static List<String> compareListNodoNotEqualSize(List<String> base, Integer desfaje, List<String> list) {

		List<String> nodos = new ArrayList<>();
		for (int i = 0; i < base.size(); i++) {
			if (i == 0) {
				nodos.add(validateCaseOne(base.get(i), list.get(i), list.get(i + desfaje)));
			} else if (i == base.size() - 1) {				
				if(base.size() < list.size()) {
					nodos.add(validateCaseTwo(base.get(i), list.get(i), list.get(i + desfaje), nodos.get(i - desfaje)));
				}else if(base.size() > list.size()){
					nodos.add(validateCaseThree(base.get(i), list.get(list.size()-1), nodos.get(i - desfaje)));
				}else {
					nodos.add(validateCaseThree(base.get(i), list.get(i), nodos.get(i - desfaje)));
				}				
			} else {
				
				if(base.size() > list.size() && i == list.size()-1) {
					nodos.add(validateCaseThree(base.get(i), list.get(i), nodos.get(i - desfaje)));
				}else {
					nodos.add(validateCaseTwo(base.get(i), list.get(i), list.get(i + desfaje), nodos.get(i - desfaje)));
				}
				
				
			}
		}

		return nodos;
	}

	private static String validateCaseThree(String base, String list, String listDesfasajeDown) {
		
		var result = "";
		
		if (!base.equals("") && !base.equals(list) && !base.equals(listDesfasajeDown)) {
			result = base;
		}else {
			if(!list.equals("") &&  !list.equals(listDesfasajeDown)) {
				result = list;
			}
		}				
		return result;
	}

	private static String validateCaseTwo(String base, String list, String listDesfasaje, String listDesfasajeDown) {
		var result = "";
		
		if (!base.equals("") && !base.equals(list) && !base.equals(listDesfasaje) && !base.equals(listDesfasajeDown)) {
			result = base;
		} else {
			if (!list.equals("") && !list.equals(listDesfasajeDown)) {
				result = list;
			}else if(list.equals(listDesfasajeDown)) {
				result = listDesfasaje;
			} 
			else if (!listDesfasaje.equals("") && base.equals(listDesfasaje)) {
				result = listDesfasaje;
			}
		}
		
		return result;
	}

	private static String validateCaseOne(String base, String list, String listDesfasaje) {

		var result = "";

		if (!base.equals("")) {			
			result = base;
		} else {
			if (!list.equals("")) {
				result = list;
			} else if (!listDesfasaje.equals("")) {
				result = listDesfasaje;
			}
		}

		return result;
	}

}
