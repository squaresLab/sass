public class Plan1571770276686 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("B");
}

DecreaseTraffic("A");

StartServer("B");

}

}
}
