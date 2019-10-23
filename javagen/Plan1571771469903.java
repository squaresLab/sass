public class Plan1571771469903 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("B");
}


}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



}
}
