public class Plan1571769387823 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
if ( StartServer("B") ) {
StartServer("A");
} else {
DecreaseTraffic("A");
}

} else {
StartServer("A");
}

}

for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

DecreaseTraffic("A");


}
}
