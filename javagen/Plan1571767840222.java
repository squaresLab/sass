public class Plan1571767840222 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("C");
}


}


}
}
