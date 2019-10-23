public class Plan1571769319231 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("C");
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {

}


}

for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

StartServer("A");


}
}
