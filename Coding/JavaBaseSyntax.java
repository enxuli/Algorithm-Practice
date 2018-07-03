public class JavaBaseSyntax {
  public static void main(String[] args){
    System.out.println("Q1");
    byte Bytevalue = 8;
    short Shortvalue = 16;
    int Intvalue = 32;
    long Longsum = (50000+10)*(Bytevalue + Shortvalue + Intvalue);
    float Floatvalue = 5.6434f;
    System.out.println(Bytevalue);
	System.out.println(Shortvalue);
	System.out.println(Intvalue);
	System.out.println(Longsum);
	System.out.println(Floatvalue);
	Boolean flag = false;
	int SelectorValueByBoolean = flag ? 1:2;
	System.out.println(SelectorValueByBoolean);

	//merge sort
	System.out.println("Merge sort result:");
	int[] a={1,2,9,4,7,3,20,13,19};
	mergeSort(a,0,8);
	for(int i=0;i<=8;i++)
	System.out.println(a[i]);

    //quick sort
	System.out.println("quick sort result:");
	int[] b={1,2,9,4,7,3,20,13,19};
	quickSort(b,0,8);
	for(int j=0;j<=8;j++)
	System.out.println(b[j]);
  }
static int partition(int arr[], int left, int right)
{
      int i = left, j = right;
      int tmp;
      int pivot = arr[(left + right) / 2];
      while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;
            if (i <= j) {
                  tmp = arr[i];
                  arr[i++] = arr[j];
                  arr[j--] = tmp;
            }
      };     
      return i;
}

static void quickSort(int arr[], int left, int right) {
      int index = partition(arr, left, right);
      if (left < index - 1)
            quickSort(arr, left, index - 1);
      if (index < right)
            quickSort(arr, index, right);
}


  static void mergeSort(int[] array, int low, int high){
	if(low < high){
		int middle = (low + high) / 2;
		mergeSort(array, low, middle);
		mergeSort(array, middle+1, high);
		merge(array, low, middle, high);
	}	
}

static void merge(int[] array, int low, int middle, int high){
	int[] tmp = new int[array.length];
	for (int i = low; i <= high; i++) {
		tmp[i] = array[i];
	}
	int pointLeft = low;
	int pointRight = middle+1;
	int current = low;
	
	while (pointLeft <= middle && pointRight <=high) {
			array[current++] = tmp[pointLeft] <= tmp[pointRight]? tmp[pointLeft++]:tmp[pointRight++];
	}
	
	int remaining = middle - pointLeft;
	for (int i = 0; i <= remaining; i++) {
		array[current++] = tmp[pointLeft++];
	}
}
}