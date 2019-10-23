public class Plan1571773475572 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("A");
} else {
DecreaseTraffic("A");
}

}

}

for (int i = 0; i < 4 ; i++) {
StartServer("B");
}


}
}
