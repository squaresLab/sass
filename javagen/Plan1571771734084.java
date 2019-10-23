public class Plan1571771734084 extends Plan { 
public static void main(String[] args) { 
DecreaseTraffic("A");
StartServer("B");

DecreaseTraffic("A");

for (int i = 0; i < 4 ; i++) {
if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("A");
}

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



}
}
