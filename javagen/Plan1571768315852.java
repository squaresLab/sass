public class Plan1571768315852 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("C");

StartServer("A");

} else {
StartServer("B");
}

StartServer("A");

}

StartServer("B");
StartServer("C");


}
}
