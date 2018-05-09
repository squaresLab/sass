public class Plan1525888974909 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("A");
if ( StartServer("C") ) {
StartServer("B");
} else {
IncreaseDimmer("A");
}


}

}
}
