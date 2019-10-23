public class Plan1571767624153 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
StartServer("A");
}

if ( StartServer("A") ) {
StartServer("A");
} else {
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

}

DecreaseTraffic("A");

StartServer("B");

StartServer("C");
DecreaseTraffic("A");

StartServer("B");


for (int i = 0; i < 3 ; i++) {
StartServer("C");
}



}
}
