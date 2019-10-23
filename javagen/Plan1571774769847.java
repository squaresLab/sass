public class Plan1571774769847 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

for (int i = 0; i < 5 ; i++) {
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
DecreaseTraffic("A");
}

} else {
StartServer("B");
}

}


}
}
